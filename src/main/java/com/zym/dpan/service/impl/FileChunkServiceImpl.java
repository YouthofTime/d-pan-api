package com.zym.dpan.service.impl;

import com.zym.dpan.constant.FileConstant;
import com.zym.dpan.dao.FileChunkDao;
import com.zym.dpan.entity.FileChunkEntity;
import com.zym.dpan.exception.RRException;
import com.zym.dpan.service.FileChunkService;
import com.zym.dpan.storage.StorageProcessorSelector;
import com.zym.dpan.utils.Constant;
import com.zym.dpan.utils.FileUtil;
import com.zym.dpan.utils.RedisKeyGenerator;
import com.zym.dpan.utils.UserIdUtil;
import com.zym.dpan.vo.FileChunkUploadVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: FileChunkServiceImpl
 * Package: com.zym.dpan.service.impl
 *
 * @Author zym
 * @Create 2024/4/8 14:10
 * @Version 1.0
 */
@Service("fileChunkService")
public class FileChunkServiceImpl implements FileChunkService {
    @Autowired
    FileChunkDao fileChunkDao;
    @Autowired
    StorageProcessorSelector storageProcessorSelector;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Override
    public List<Integer> getUploadedChunkNumbers(String identifier,Long userId) {
        List<FileChunkEntity> fileChunkEntities = fileChunkDao.selectAllByIdentifier(identifier,userId);
        List<Integer> chunkNumbers = fileChunkEntities.stream().map(FileChunkEntity::getChunkNumber).collect(Collectors.toList());
        return chunkNumbers;
    }

    @Override
    public Integer saveWithChunk(FileChunkUploadVo fileChunkUploadVo) {

        MultipartFile file = fileChunkUploadVo.getFile();
        String identifier = fileChunkUploadVo.getIdentifier();
        Integer chunkNumber = fileChunkUploadVo.getChunkNumber();
        Long chunkSize = file.getSize();
        String suffix = FileUtil.getFileSuffix(fileChunkUploadVo.getFilename());
        Long userId = UserIdUtil.get();
        String chunkFilePath="";
        // 1.执行分块上传
        try {
            chunkFilePath = storageProcessorSelector.select().storeWitchChunk(file.getInputStream(), identifier, chunkNumber, chunkSize);
        } catch (IOException e) {
            throw new RRException("上传文件失败");
        }
        // 2.保存分块上传记录
        FileChunkEntity fileChunkEntity = new FileChunkEntity();
        BeanUtils.copyProperties(fileChunkUploadVo,fileChunkEntity);
        fileChunkEntity.setRealPath(chunkFilePath);
        fileChunkDao.insert(fileChunkEntity);
        // 3.查询已上传分块数量，和总分块数量比较，返回合并标志
        Integer uploadedChunkCount = fileChunkDao.selectUploadedChunkCount(identifier,userId);
        Integer mergeFlag = Constant.ChunkMergeFlagEnum.NOT_READY.getCode();
        // 如果满足合并要求，添加缓存 userId和identifier作为key,suffix作为value
        if(uploadedChunkCount.equals(fileChunkUploadVo.getTotalChunks())){
            String chunkKey = RedisKeyGenerator.generateChunkKey(identifier,userId);
            redisTemplate.opsForValue().set(chunkKey,suffix);
            mergeFlag = Constant.ChunkMergeFlagEnum.READY.getCode();
        }
        return mergeFlag;
    }

}

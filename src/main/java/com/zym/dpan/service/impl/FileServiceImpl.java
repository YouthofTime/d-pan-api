package com.zym.dpan.service.impl;

import com.zym.dpan.dao.FileChunkDao;
import com.zym.dpan.dao.FileDao;
import com.zym.dpan.entity.FileChunkEntity;
import com.zym.dpan.entity.FileEntity;
import com.zym.dpan.exception.RRException;
import com.zym.dpan.service.FileService;
import com.zym.dpan.storage.StorageProcessorSelector;
import com.zym.dpan.utils.FileUtil;
import com.zym.dpan.utils.RedisKeyGenerator;
import com.zym.dpan.utils.UserIdUtil;
import com.zym.dpan.vo.FileChunkMergeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * ClassName: FileServiceImpl
 * Package: com.zym.dpan.service.impl
 *
 * @Author zym
 * @Create 2024/4/7 18:55
 * @Version 1.0
 */
@Service("fileService")
public class FileServiceImpl implements FileService {
    @Autowired
    FileDao fileDao;
    @Autowired
    FileChunkDao fileChunkDao;
    @Autowired
    StorageProcessorSelector storageProcessorSelector;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Override
    public FileEntity selectByIdentifier(String identifier) {
        return fileDao.selectByIdentifier(identifier);
    }

    @Override
    public FileEntity mergeWithChunks(FileChunkMergeVo fileChunkMergeVo){
        // 1.分片合并
        // 从缓存中查询出合并文件后缀
        String fileSuffix = fileChunkMergeVo.getFilename();
        Integer totalSize = fileChunkMergeVo.getTotalSize();
        String identifier = fileChunkMergeVo.getIdentifier();
        Long userId = UserIdUtil.get();
        String chunkKey = RedisKeyGenerator.generateChunkKey(identifier,userId);
        String suffix = stringRedisTemplate.opsForValue().get(chunkKey);
        // 查询出所有分片
        List<FileChunkEntity> fileChunkEntities = fileChunkDao.selectAllByIdentifier(identifier, userId);
        String mergeFilePath = "";
        // 将分片依次写入文件，返回文件路径
        try {
            mergeFilePath = storageProcessorSelector.select().storeMergeChunks(suffix,fileChunkEntities);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RRException("合并失败");
        }
        // 删除分片信息
        fileChunkDao.deleteByIdentifierAndUserId(identifier,userId);
        // 删除缓存
        stringRedisTemplate.delete(chunkKey);
        // 2.保存文件信息
        FileEntity fileEntity = new FileEntity();
        fileEntity.setRealPath(mergeFilePath);
        fileEntity.setFilename(FileUtil.getFilename(mergeFilePath));
        fileEntity.setFileSize(String.valueOf(totalSize));
        fileEntity.setFileSizeDesc(FileUtil.getFileSizeDesc(totalSize));
        fileEntity.setFileSuffix(fileSuffix);
        fileEntity.setFilePreviewContentType(FileUtil.getContentType(mergeFilePath));
        fileEntity.setIdentifier(identifier);
        fileDao.insert(fileEntity);
        return fileEntity;

    }
}

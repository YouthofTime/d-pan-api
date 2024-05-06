package com.zym.dpan.service.impl;
import com.zym.dpan.constant.FileConstant;
import com.zym.dpan.constant.UserFileConstant;
import com.zym.dpan.constant.UserFileEnum;
import com.zym.dpan.dao.FileDao;
import com.zym.dpan.dao.UserFileDao;
import com.zym.dpan.entity.FileEntity;
import com.zym.dpan.entity.UserFileEntity;
import com.zym.dpan.exception.RRException;
import com.zym.dpan.service.FileService;
import com.zym.dpan.service.UserFileService;
import com.zym.dpan.storage.StorageProcessorSelector;
import com.zym.dpan.utils.FileTypeClassifier;
import com.zym.dpan.utils.UserIdUtil;
import com.zym.dpan.vo.FileSecUploadVo;
import com.zym.dpan.vo.FolderTreeNodeRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * ClassName: UserFileServiceImpl
 * Package: com.zym.dpan.service
 *
 * @Author zym
 * @Create 2024/4/7 18:46
 * @Version 1.0
 */
@Slf4j
@Service("userFileService")
public class UserFileServiceImpl implements UserFileService {
    @Autowired
    FileService fileService;
    @Autowired
    UserFileDao userFileDao;
    @Autowired
    FileDao fileDao;
    @Autowired
    StorageProcessorSelector storageProcessorSelector;
    @Override
    public boolean secUpload(FileSecUploadVo fileSecUploadVo) {
        // 1.查询该文件是否已经上传
        FileEntity fileEntity = fileService.selectByIdentifier(fileSecUploadVo.getIdentifier());
        // 2.未上传：过直接返回，后续进行分块上传
        if(fileEntity==null){
            return false;
        }
        // 3.上传过：保存本次文件上传记录
        UserFileEntity userFileEntity = new UserFileEntity();
        // filename和fileEntity中的filename是不一样的
        BeanUtils.copyProperties(fileSecUploadVo,userFileEntity);
        userFileEntity.setFileSizeDesc(fileEntity.getFileSizeDesc());
        userFileEntity.setRealFileId(fileEntity.getFileId());
        // 文件类型设置
        FileConstant.FileType fileType = FileTypeClassifier.classifyFileType(fileSecUploadVo.getFilename());
        userFileEntity.setFileType(fileType.getCode());
        userFileDao.insert(userFileEntity);
        return true;
    }

    @Override
    public void download(Long fileId, Long userId, HttpServletResponse response) {
        // 查询该文件是否存在
        UserFileEntity userFileEntity = userFileDao.selectByFileIdAndUserId(fileId, userId);
        if(userFileEntity==null){
            throw new RRException("您没有下载权限");
        }
        if(userFileEntity.getDelFlag().equals(UserFileEnum.DEL_FLAG_TRUE.getCode())){
            throw new RRException("您没有下载权限");
        }
        // 判断是否为文件夹
        if(userFileEntity.getFolderFlag().equals(UserFileEnum.FOLDER_FLAG_TRUE.getCode())){
            throw new RRException("不能下载文件夹");
        }
        // 查询出真实路径和文件名字
        FileEntity fileEntity = fileDao.selectByPrimaryKey(userFileEntity.getRealFileId());

        // 添加响应头
        try {
            addResponseHeader(response,userFileEntity.getFilename());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error("文件名编码错误，下载文件失败");
        }
        // 下载文件
        try {
            storageProcessorSelector.select().download(fileEntity.getRealPath(),response);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("写入失败");
        }
    }

    @Override
    public List<UserFileEntity> list(Long parentId) {
        Integer delFlag = UserFileEnum.DEL_FLAG_FALSE.getCode();
        Long userId = UserIdUtil.get();

        return userFileDao.selectByParentId(parentId,delFlag,userId);
    }

    @Override
    public List<FolderTreeNodeRespVo> getFolderTree() {
        Long userId = UserIdUtil.get();
        // 1.查询出当前用户下的所有文件
        List<UserFileEntity> userFileEntityList = userFileDao.selectFolderListByUserId(userId,
                UserFileEnum.DEL_FLAG_FALSE.getCode(),UserFileEnum.FOLDER_FLAG_TRUE.getCode());
        List<FolderTreeNodeRespVo> folderTreeNodeList = userFileEntityList.stream().map(FolderTreeNodeRespVo::setFolderTreeNode)
                .collect(Collectors.toList());
        // 2.根据parentId进行分组
        Map<Long, List<FolderTreeNodeRespVo>> treeNodeParentGroup = folderTreeNodeList.stream().collect(Collectors.groupingBy(FolderTreeNodeRespVo::getParentId));
        // 3.设置每个节点的孩子节点
        folderTreeNodeList.stream().forEach(node->{
            List<FolderTreeNodeRespVo> children = treeNodeParentGroup.get(node.getId());
            if(!CollectionUtils.isEmpty(children)){
                node.setChildren(children);
            }

        });
        return folderTreeNodeList.stream().filter(item->item.getParentId().equals(UserFileConstant.ROOT_PARENT_ID)).collect(Collectors.toList());
    }

    /**
     * 获取用户的顶级文件信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserFileEntity getUserTopFileInfo(Long userId) {
        return userFileDao.selectTopFolderByUserId(userId);
    }

    @Override
    public void createFolder(Long parentId, String folderName, Long userId) {
        UserFileEntity userFileEntity = new UserFileEntity();
        userFileEntity.setFolderFlag(UserFileEnum.FOLDER_FLAG_TRUE.getCode());
        userFileEntity.setParentId(UserFileConstant.ROOT_PARENT_ID);
        userFileEntity.setFilename(folderName);
        userFileEntity.setUserId(userId);
        userFileDao.insert(userFileEntity);
    }

    /**
     * 添加响应头
     * @param response
     * @param filename
     */
    private void addResponseHeader(HttpServletResponse response,String filename) throws UnsupportedEncodingException {
        response.reset();
        // 1.跨域
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Max-Age","3600");
        // 2.下载名字
        // 将文件名转换为ASCII字符集
        String asciiFilename = new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.US_ASCII);
// 使用UTF-8编码对文件名进行URL编码
        String encodedFilename = URLEncoder.encode(asciiFilename, String.valueOf(StandardCharsets.UTF_8));
        response.setHeader("Content-Disposition", "attachment; fileName="+encodedFilename);
        // 3.下载类型
        response.setContentType("application/octet-stream");
        response.setHeader("content-type","application/octet-stream");
    }

}

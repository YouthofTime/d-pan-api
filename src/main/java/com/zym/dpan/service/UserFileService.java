package com.zym.dpan.service;

import com.zym.dpan.entity.UserFileEntity;
import com.zym.dpan.vo.FileSecUploadVo;
import com.zym.dpan.vo.resp.FolderTreeNodeRespVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName: UserFileService
 * Package: com.zym.dpan.service
 *
 * @Author zym
 * @Create 2024/4/7 18:45
 * @Version 1.0
 */
public interface UserFileService {

    boolean secUpload(FileSecUploadVo fileSecUploadVo);

    void download(Long fileId, Long userId, HttpServletResponse response);

    List<UserFileEntity> list(Long parentId);

    List<FolderTreeNodeRespVo> getFolderTree();

    UserFileEntity getUserTopFileInfo(Long userId);

    void createFolder(Long parentId, String folderName, Long userId);
}

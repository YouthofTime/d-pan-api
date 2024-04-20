package com.zym.dpan.service;

import com.zym.dpan.vo.FileSecUploadVo;

import javax.servlet.http.HttpServletResponse;

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
}

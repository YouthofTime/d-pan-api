package com.zym.dpan.service;

import com.zym.dpan.vo.FileSecUploadVo;

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
}

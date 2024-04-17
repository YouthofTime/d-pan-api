package com.zym.dpan.service;

import com.zym.dpan.entity.FileEntity;
import com.zym.dpan.vo.FileChunkMergeVo;

/**
 * ClassName: FileService
 * Package: com.zym.dpan.service
 *
 * @Author zym
 * @Create 2024/4/7 18:55
 * @Version 1.0
 */
public interface FileService {
    FileEntity selectByIdentifier(String identifier);

    FileEntity mergeWithChunks(FileChunkMergeVo fileChunkMergeVo);
}

package com.zym.dpan.service;

import com.zym.dpan.vo.FileChunkMergeVo;
import com.zym.dpan.vo.FileChunkUploadVo;

import java.util.List;

/**
 * ClassName: FileChunkService
 * Package: com.zym.dpan.service
 *
 * @Author zym
 * @Create 2024/4/8 14:06
 * @Version 1.0
 */
public interface FileChunkService {
    List<Integer> getUploadedChunkNumbers(String identifier,Long userId);

    Integer saveWithChunk(FileChunkUploadVo fileChunkUploadVo);

    void mergeWithChunks(FileChunkMergeVo fileChunkMergeVo);
}

package com.zym.dpan.storage;

import com.zym.dpan.entity.FileChunkEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * ClassName: StorageProcessor
 * Package: com.zym.dpan.storage
 *
 * @Author zym
 * @Create 2024/4/8 17:41
 * @Version 1.0
 */
public interface StorageProcessor {
    String storeWitchChunk(InputStream inputStream, String identifier,Long userId, String suffix, Integer chunkNumber, Long chunkSize)throws IOException;

    String storeMergeChunks(String suffix, List<FileChunkEntity> fileChunkEntities) throws IOException;

    void download(String filePath, HttpServletResponse response) throws IOException;
}

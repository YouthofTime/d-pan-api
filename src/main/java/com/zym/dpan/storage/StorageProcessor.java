package com.zym.dpan.storage;

import java.io.IOException;
import java.io.InputStream;

/**
 * ClassName: StorageProcessor
 * Package: com.zym.dpan.storage
 *
 * @Author zym
 * @Create 2024/4/8 17:41
 * @Version 1.0
 */
public interface StorageProcessor {
    String storeWitchChunk(InputStream inputStream, String identifier, Integer chunkNumber, Long chunkSize)throws IOException;
}

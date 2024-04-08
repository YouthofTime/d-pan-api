package com.zym.dpan.storage.impl;

import com.zym.dpan.storage.StorageProcessor;
import com.zym.dpan.utils.FileUtil;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * ClassName: LocalStorageProcessor
 * Package: com.zym.dpan.storage
 *
 * @Author zym
 * @Create 2024/4/8 17:40
 * @Version 1.0
 */
@Component(value = "localStorageProcessor")
public class LocalStorageProcessor implements StorageProcessor {

    @Override
    public String storeWitchChunk(InputStream inputStream, String identifier, Integer chunkNumber, Long chunkSize)throws IOException {
        // 生成分片绝对路径
        String chunkFilePath = FileUtil.generateChunkFilePath(identifier,chunkNumber);
        // 保存分片
        FileUtil.writeStreamToFile(inputStream,new File(chunkFilePath),chunkSize);
        return chunkFilePath;
    }
}

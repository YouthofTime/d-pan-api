package com.zym.dpan.storage.impl;

import com.zym.dpan.entity.FileChunkEntity;
import com.zym.dpan.storage.StorageProcessor;
import com.zym.dpan.utils.FileUtil;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public String storeMergeChunks(String suffix, List<FileChunkEntity> fileChunkEntities) throws IOException {
        // 创建合并后的文件
        String mergeFilePath = FileUtil.generateMergeFilePath(suffix);
        FileUtil.createFile(mergeFilePath);
        // 对分片按照分片号进行排序
        List<FileChunkEntity> sortedFileChunkEntities = fileChunkEntities.stream()
                .sorted(Comparator.comparing(FileChunkEntity::getChunkNumber))
                .collect(Collectors.toList());
        // 将排序好的分片依次写入文件，并删除已经写完的分片
        for(FileChunkEntity fileChunkEntity:sortedFileChunkEntities){
            File chunkFile = new File(fileChunkEntity.getRealPath());
            Files.write(Paths.get(mergeFilePath),
                    Files.readAllBytes(chunkFile.toPath()),
                    StandardOpenOption.APPEND);
            Files.delete(chunkFile.toPath());
        }
        return mergeFilePath;
    }


}

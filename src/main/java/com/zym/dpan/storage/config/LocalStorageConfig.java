package com.zym.dpan.storage.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ClassName: LocalStorageConfig
 * Package: com.zym.dpan.storage.config
 *
 * @Author zym
 * @Create 2024/5/6 16:05
 * @Version 1.0
 */
@Component(value = "localStorageConfig")
@Data
public class LocalStorageConfig {
    @Value("${dpan.storage.local.rootFilePath}")
    private String rootFilePath;
    @Value("${dpan.storage.local.tempPath}")
    private String tempPath;
}

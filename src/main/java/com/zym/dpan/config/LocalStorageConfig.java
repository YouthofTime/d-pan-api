package com.zym.dpan.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName: LocalStorageConfig
 * Package: com.zym.dpan.config
 *
 * @Author zym
 * @Create 2024/4/8 20:22
 * @Version 1.0
 */
@Component(value = "localStorageConfig")
@ConfigurationProperties(prefix = "rpan.storage.local")
@ConditionalOnProperty(prefix = "rpan.storage.processor", name = "type", havingValue = "com.rubin.rpan.storage.local.LocalStorageProcessor")
@Data
public class LocalStorageConfig {
    private String rootFilePath;

    private String tempPath;
}

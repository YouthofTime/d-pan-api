package com.zym.dpan.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * ClassName: StorageProcessorSelector
 * Package: com.zym.dpan.storage
 *根据配置信息提供对应的存储处理器
 * @Author zym
 * @Create 2024/4/8 18:35
 * @Version 1.0
 */
@Component(value = "storageProcessorSelector")
public class StorageProcessorSelector {
    @Autowired
    @Qualifier("localStorageProcessor")
    StorageProcessor storageProcessor;
    public StorageProcessor select(){
        return storageProcessor;
    }
}

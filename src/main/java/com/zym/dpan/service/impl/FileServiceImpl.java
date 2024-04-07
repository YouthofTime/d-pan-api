package com.zym.dpan.service.impl;

import com.zym.dpan.dao.FileDao;
import com.zym.dpan.entity.FileEntity;
import com.zym.dpan.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * ClassName: FileServiceImpl
 * Package: com.zym.dpan.service.impl
 *
 * @Author zym
 * @Create 2024/4/7 18:55
 * @Version 1.0
 */
@Service("fileService")
public class FileServiceImpl implements FileService {
    @Autowired
    @Qualifier(value = "fileDao")
    FileDao fileDao;
    @Override
    public FileEntity selectByIdentifier(String identifier) {
        return fileDao.selectByIdentifier(identifier);
    }
}

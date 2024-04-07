package com.zym.dpan.dao;

import com.zym.dpan.entity.FileEntity;

import org.springframework.stereotype.Repository;

/**
 * 物理文件信息表
 * 
 * @author zym
 * @email zym@gmail.com
 * @date 2024-04-07 17:20:22
 */
@Repository(value = "fileDao")
public interface FileDao{
    FileEntity selectByIdentifier(String identifier);
}

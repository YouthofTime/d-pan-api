package com.zym.dpan.dao;

import com.zym.dpan.entity.UserFileEntity;
import org.springframework.stereotype.Repository;

/**
 * 用户文件信息表
 * 
 * @author zym
 * @email zym@gmail.com
 * @date 2024-04-07 17:20:22
 */
@Repository(value = "userFileDao")
public interface UserFileDao{
    int insert(UserFileEntity userFileEntity);
}

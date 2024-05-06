package com.zym.dpan.dao;

import com.zym.dpan.entity.UserFileEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    UserFileEntity selectByFileIdAndUserId(@Param("fileId") Long fileId,@Param("userId") Long userId);

    List<UserFileEntity> selectByParentId(@Param("parentId")Long parentId, @Param("delFlag") Integer delFlag,@Param("userId") Long userId);

    List<UserFileEntity> selectFolderListByUserId(@Param("userId") Long userId, @Param("delFlag") Integer delFlag,@Param("folderFlag") Integer folderFlag);

    UserFileEntity selectTopFolderByUserId(@Param("userId") Long userId);
}

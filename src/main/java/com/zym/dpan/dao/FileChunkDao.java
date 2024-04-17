package com.zym.dpan.dao;


import com.zym.dpan.entity.FileChunkEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件分片信息表
 * 
 * @author zym
 * @email zym@gmail.com
 * @date 2024-04-07 17:20:22
 */
@Mapper
public interface FileChunkDao{
	List<FileChunkEntity> selectAllByIdentifier(String identifier,Long userId);

	int insert(FileChunkEntity fileChunkEntity);

	int selectUploadedChunkCount(@Param("identifier") String identifier, @Param("userId") Long userId);

    void deleteByIdentifierAndUserId(String identifier, Long userId);
}

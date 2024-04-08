package com.zym.dpan.entity;



import java.io.Serializable;
import java.util.Date;

import com.zym.dpan.constant.UserFileEnum;
import com.zym.dpan.utils.IdGenerator;
import com.zym.dpan.utils.UserIdUtil;
import lombok.Data;

/**
 * 文件分片信息表
 * 
 * @author zym
 * @email zym@gmail.com
 * @date 2024-04-07 17:20:22
 */
@Data

public class FileChunkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */

	private Long id;
	/**
	 * 文件唯一标识
	 */
	private String identifier;
	/**
	 * 分片真实的存储路径
	 */
	private String realPath;
	/**
	 * 分片编号
	 */
	private Integer chunkNumber;
	/**
	 * 过期时间
	 */
	private Date expirationTime;
	/**
	 * 创建人
	 */
	private Long createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public FileChunkEntity(){
		Long userId = UserIdUtil.get();
		this.setCreateTime(new Date());
		this.setCreateUser(userId);
		// TODO 设置过期时间：当前日期的下一天
		//this.setExpirationTime();
	}

}

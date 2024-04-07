package com.zym.dpan.entity;


import java.io.Serializable;
import java.util.Date;

import com.zym.dpan.constant.UserFileEnum;
import com.zym.dpan.utils.IdGenerator;
import com.zym.dpan.utils.UserIdUtil;
import lombok.Data;

/**
 * 用户文件信息表
 * 
 * @author zym
 * @email zym@gmail.com
 * @date 2024-04-07 17:20:22
 */
@Data
public class UserFileEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 文件记录ID
	 */
	private Long fileId;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 上级文件夹ID,顶级文件夹为0
	 */
	private Long parentId;
	/**
	 * 真实文件id
	 */
	private Long realFileId;
	/**
	 * 文件名
	 */
	private String filename;
	/**
	 * 是否是文件夹 （0 否 1 是）
	 */
	private Integer folderFlag;
	/**
	 * 文件大小展示字符
	 */
	private String fileSizeDesc;
	/**
	 * 文件类型（1 普通文件 2 压缩文件 3 excel 4 word 5 pdf 6 txt 7 图片 8 音频 9 视频 10 ppt 11 源码文件 12 csv）
	 */
	private Integer fileType;
	/**
	 * 删除标识（0 否 1 是）
	 */
	private Integer delFlag;
	/**
	 * 创建人
	 */
	private Long createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新人
	 */
	private Long updateUser;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public void setDefaultValue(){
		Long userId = UserIdUtil.get();
		this.setCreateTime(new Date());
		this.setUpdateTime(new Date());
		this.setUserId(userId);
		this.setCreateUser(userId);
		this.setUpdateUser(userId);
		this.setFolderFlag(UserFileEnum.FOLDER_FLAG_FASE.getCode());
		this.setFileId(IdGenerator.nextId());
		this.setDelFlag(UserFileEnum.DEL_FLAG_FALSE.getCode());
		this.setFileType(UserFileEnum.FILE_TYPE_NONE.getCode());
	}
}

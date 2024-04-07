package com.zym.dpan.entity;



import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户分享文件表
 * 
 * @author zym
 * @email zym@gmail.com
 * @date 2024-04-07 17:20:22
 */
@Data

public class ShareFileEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */

	private Long id;
	/**
	 * 分享id
	 */
	private Long shareId;
	/**
	 * 文件记录ID
	 */
	private Long fileId;
	/**
	 * 分享创建人
	 */
	private Long createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;

}

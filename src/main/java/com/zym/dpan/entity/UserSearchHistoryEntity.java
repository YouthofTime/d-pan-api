package com.zym.dpan.entity;



import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户搜索历史表
 * 
 * @author zym
 * @email zym@gmail.com
 * @date 2024-04-07 17:20:22
 */
@Data

public class UserSearchHistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */

	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 搜索文案
	 */
	private String searchContent;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}

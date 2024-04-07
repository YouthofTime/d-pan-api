package com.zym.dpan.entity;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 错误日志表
 * 
 * @author zym
 * @email zym@gmail.com
 * @date 2024-04-07 17:20:22
 */
@Data
public class ErrorLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 日志内容
	 */
	private String logContent;
	/**
	 * 日志状态：0 未处理 1 已处理
	 */
	private Integer logStatus;
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

}

package com.zym.dpan.entity;



import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户分享表
 * 
 * @author zym
 * @email zym@gmail.com
 * @date 2024-04-07 17:20:22
 */
@Data
public class ShareEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分享id
	 */
	private Long shareId;
	/**
	 * 分享名称
	 */
	private String shareName;
	/**
	 * 分享类型（0 有提取码）
	 */
	private Integer shareType;
	/**
	 * 分享类型（0 永久有效；1 7天有效；2 30天有效）
	 */
	private Integer shareDayType;
	/**
	 * 分享有效天数（永久有效为0）
	 */
	private Integer shareDay;
	/**
	 * 分享结束时间
	 */
	private Date shareEndTime;
	/**
	 * 分享链接地址
	 */
	private String shareUrl;
	/**
	 * 分享提取码
	 */
	private String shareCode;
	/**
	 * 分享状态（0 正常；1 有文件被删除）
	 */
	private Integer shareStatus;
	/**
	 * 分享创建人
	 */
	private Long createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;

}

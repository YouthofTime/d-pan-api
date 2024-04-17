package com.zym.dpan.entity;



import java.io.Serializable;
import java.util.Date;

import com.zym.dpan.utils.IdGenerator;
import com.zym.dpan.utils.UserIdUtil;
import lombok.Data;

/**
 * 物理文件信息表
 * 
 * @author zym
 * @email zym@gmail.com
 * @date 2024-04-07 17:20:22
 */
@Data
public class FileEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 文件id
	 */
	private Long fileId;
	/**
	 * 文件名称
	 */
	private String filename;
	/**
	 * 文件物理路径
	 */
	private String realPath;
	/**
	 * 文件实际大小
	 */
	private String fileSize;
	/**
	 * 文件大小展示字符
	 */
	private String fileSizeDesc;
	/**
	 * 文件后缀
	 */
	private String fileSuffix;
	/**
	 * 文件预览的响应头Content-Type的值
	 */
	private String filePreviewContentType;
	/**
	 * 文件唯一标识
	 */
	private String identifier;
	/**
	 * 创建人
	 */
	private Long createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public FileEntity(){
		fileId = IdGenerator.nextId();
		createUser = UserIdUtil.get();
		createTime = new Date();
	}

}

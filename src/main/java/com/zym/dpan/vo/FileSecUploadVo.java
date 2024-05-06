package com.zym.dpan.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ClassName: FileSecUploadVo
 * Package: com.zym.dpan.vo
 *
 * @Author zym
 * @Create 2024/4/7 18:25
 * @Version 1.0
 */
@Data
public class FileSecUploadVo {

//    @NotBlank(message = "文件名称不能为空")
    private String filename;

//    @NotBlank(message = "文件唯一标识不能为空")
    private String identifier;

//    @NotNull(message = "父id不能为空")
    private Long parentId;
}

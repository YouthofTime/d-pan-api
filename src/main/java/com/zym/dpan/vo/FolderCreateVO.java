package com.zym.dpan.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ClassName: FolderCreateVO
 * Package: com.zym.dpan.vo
 *
 * @Author zym
 * @Create 2024/5/10 13:56
 * @Version 1.0
 */
@Data
public class FolderCreateVO {

    @NotBlank(message = "文件夹名称不能为空")
    private String folderName;

    @NotNull(message = "父id不能为空")
    private Long parentId;
}

package com.zym.dpan.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ClassName: FileMergeUploadVo
 * Package: com.zym.dpan.vo
 *
 * @Author zym
 * @Create 2024/4/17 14:31
 * @Version 1.0
 */
@Data
public class FileChunkMergeVo {
    @NotBlank(message = "文件名称不能为空")
    private String filename;

    @NotBlank(message = "文件唯一标识不能为空")
    private String identifier;

    @NotNull(message = "文件大小不能为空")
    private Integer totalSize;

    @NotNull(message = "父id不能为空")
    private Long parentId;
}

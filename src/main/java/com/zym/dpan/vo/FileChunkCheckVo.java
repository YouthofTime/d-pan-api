package com.zym.dpan.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: FileChunkCheckVo
 * Package: com.zym.dpan.vo
 *
 * @Author zym
 * @Create 2024/5/6 16:23
 * @Version 1.0
 */
@Data
public class FileChunkCheckVo {
    @NotBlank(message = "文件唯一标识不能为空")
    private String identifier;
}

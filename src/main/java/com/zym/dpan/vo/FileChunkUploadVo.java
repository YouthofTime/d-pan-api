package com.zym.dpan.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * ClassName: FileChunkUploadVo
 * Package: com.zym.dpan.vo
 *
 * @Author zym
 * @Create 2024/4/8 15:32
 * @Version 1.0
 */
@Data
public class FileChunkUploadVo {

    @NotBlank(message = "文件名称不能为空")
    private String filename;

    @NotBlank(message = "文件唯一标识不能为空")
    private String identifier;

    @NotNull(message = "总分片数不能为空")
    private Integer totalChunks;

    @NotNull(message = "分片编号不能为空")
    @Min(1)
    private Integer chunkNumber;

    @NotNull(message = "当前分片大小不能为空")
    private Integer currentChunkSize;

    @NotNull(message = "文件大小不能为空")
    private Integer totalSize;

    @NotNull(message = "上传文件不能为空")
    private MultipartFile file;

}

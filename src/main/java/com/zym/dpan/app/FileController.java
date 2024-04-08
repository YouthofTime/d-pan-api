package com.zym.dpan.app;

import com.zym.dpan.service.FileChunkService;
import com.zym.dpan.service.UserFileService;
import com.zym.dpan.utils.R;
import com.zym.dpan.vo.FileChunkUploadVo;
import com.zym.dpan.vo.FileSecUploadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * ClassName: FileController
 * Package: com.zym.dpan.app
 *
 * @Author zym
 * @Create 2024/4/7 15:39
 * @Version 1.0
 */
@RestController
@RequestMapping("file")
@Validated
public class FileController {
    @Autowired
    UserFileService userFileService;

    @Autowired
    FileChunkService fileChunkService;

    /**
     * 秒级上传：物理文件存在，上传一份用户文件记录
     * @param fileSecUploadVo
     * @return
     */
    @PostMapping("/sec-upload")
    public R secUpload(@Validated FileSecUploadVo fileSecUploadVo){
        if(userFileService.secUpload(fileSecUploadVo)){
            return R.ok();
        }
        return R.error("文件唯一标识不存在，请执行物理上传");
    }

    /**
     * 分片上传
     * @param identifier
     * @return
     */
    @RequestMapping("/chunk-upload/{identifier}")
    public R checkUploadWithChunk(@PathVariable("identifier") @Valid @NotBlank(message = "文件唯一标识不能为空") String identifier){
        List<Integer> uploadedChunks =  fileChunkService.getUploadedChunkNumbers(identifier);
        return R.ok().data("uploadedChunks",uploadedChunks);
    }

    @PostMapping("/chunk-upload")
    public R uploadWithChunk(@Validated FileChunkUploadVo fileChunkUploadVo){
        Integer mergeFlag = fileChunkService.saveWithChunk(fileChunkUploadVo);
        return R.ok().data("mergeFlag",mergeFlag);
    }
}

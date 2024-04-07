package com.zym.dpan.app;

import com.zym.dpan.service.UserFileService;
import com.zym.dpan.utils.R;
import com.zym.dpan.vo.FileSecUploadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class FileController {
    @Autowired
    UserFileService userFileService;
    @PostMapping("/sec-upload")
    public R secUpload(@Validated @RequestBody FileSecUploadVo fileSecUploadVo){
        if(userFileService.secUpload(fileSecUploadVo)){
            return R.ok();
        }
        return R.error("文件唯一标识不存在，请执行物理上传");
    }
}

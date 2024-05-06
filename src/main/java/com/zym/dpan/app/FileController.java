package com.zym.dpan.app;

import com.zym.dpan.annotation.NeedLogin;
import com.zym.dpan.entity.UserFileEntity;
import com.zym.dpan.service.FileChunkService;
import com.zym.dpan.service.FileService;
import com.zym.dpan.service.UserFileService;
import com.zym.dpan.utils.R;
import com.zym.dpan.utils.UserIdUtil;
import com.zym.dpan.vo.*;
import com.zym.dpan.vo.resp.FileChunkCheckRespVo;
import com.zym.dpan.vo.resp.FileChunkUploadRespVo;
import com.zym.dpan.vo.resp.FolderTreeNodeRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
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

    @Autowired
    FileService fileService;

    /**
     * 秒级上传：物理文件存在，上传一份用户文件记录
     * @param fileSecUploadVo
     * @return
     */
    @PostMapping("/sec-upload")
    @NeedLogin
    public R secUpload(@RequestBody @Validated FileSecUploadVo fileSecUploadVo){
        if(userFileService.secUpload(fileSecUploadVo)){
            return R.success();
        }
        return R.fail("文件唯一标识不存在，请执行物理上传");
    }

    /**
     * 分片上传：查询已上传分片号
     * @return
     */
    @GetMapping("/chunk-upload")
    @NeedLogin
    public R<FileChunkCheckRespVo> checkUploadWithChunk(@Validated FileChunkCheckVo fileChunkCheckVo){
        List<Integer> uploadedChunks =  fileChunkService.getUploadedChunkNumbers(fileChunkCheckVo.getIdentifier(), UserIdUtil.get());
        FileChunkCheckRespVo fileChunkCheckRespVo = new FileChunkCheckRespVo();
        fileChunkCheckRespVo.setUploadedChunks(uploadedChunks);
        return R.data(fileChunkCheckRespVo);
    }

    /**
     * 单个分片上传
     * @param fileChunkUploadVo
     * @return
     */
    @PostMapping("/chunk-upload")
    @NeedLogin
    public R<FileChunkUploadRespVo> uploadWithChunk(@Validated FileChunkUploadVo fileChunkUploadVo){
        Integer mergeFlag = fileChunkService.saveWithChunk(fileChunkUploadVo);
        FileChunkUploadRespVo fileChunkUploadRespVo = new FileChunkUploadRespVo();
        fileChunkUploadRespVo.setMergeFlag(mergeFlag);
        return R.data(fileChunkUploadRespVo);
    }

    /**
     * 合并分片
     * @param fileChunkMergeVo
     * @return
     */
    @PostMapping("/merge")
    @NeedLogin
    public R mergeWithChunks(@Validated @RequestBody FileChunkMergeVo fileChunkMergeVo){
        fileChunkService.mergeWithChunks(fileChunkMergeVo);
        return R.success();
    }

    /**
     * 下载文件单个
     * @param fileId
     * @param response
     */
    @GetMapping("/download")
    @NeedLogin
    public void download(@NotNull(message = "选择要下载的文件")@RequestParam(value = "fileId", required = false) Long fileId, HttpServletResponse response){
        userFileService.download(fileId,UserIdUtil.get(),response);
    }

    /**
     * 查询某个目录下的文件
     * @param parentId
     * @param fileTypes
     * @return
     */
    @RequestMapping("/list")
    @NeedLogin
    public R<List<UserFileEntity>> list(@NotNull(message = "父id不能为空")@RequestParam(value = "parentId", required = false) Long parentId,
                  @RequestParam(value = "fileTypes",required = false,defaultValue = "-1")String fileTypes){
        List<UserFileEntity> data=userFileService.list(parentId);
        return R.data(data);
    }

    @RequestMapping("/folder/tree")
    @NeedLogin
    public R getFolderTree(){
        List<FolderTreeNodeRespVo> folderTreeNodeRespVos=userFileService.getFolderTree();
        return R.success();
    }

}

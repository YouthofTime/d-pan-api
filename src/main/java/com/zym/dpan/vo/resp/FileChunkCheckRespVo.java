package com.zym.dpan.vo.resp;

import lombok.Data;

import java.util.List;

/**
 * ClassName: FileChunkCheckRespVo
 * Package: com.zym.dpan.vo.resp
 *
 * @Author zym
 * @Create 2024/5/6 16:57
 * @Version 1.0
 */
@Data
public class FileChunkCheckRespVo {

    private List<Integer> uploadedChunks;

}

package com.zym.dpan.vo.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zym.dpan.entity.UserFileEntity;
import lombok.Data;

/**
 * ClassName: BreadCrumbsRespVo
 * Package: com.zym.dpan.vo.resp
 *
 * @Author zym
 * @Create 2024/5/10 15:04
 * @Version 1.0
 */
@Data
public class BreadCrumbsRespVo {
    /**
     * 面包屑id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 面包屑名称
     */
    private String name;

    /**
     * 面包屑父id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    public static BreadCrumbsRespVo setBreadCrumb(UserFileEntity userFileEntity){
        BreadCrumbsRespVo breadCrumbsRespVo = new BreadCrumbsRespVo();
        breadCrumbsRespVo.setId(userFileEntity.getFileId());
        breadCrumbsRespVo.setName(userFileEntity.getFilename());
        breadCrumbsRespVo.setParentId(userFileEntity.getParentId());
        return breadCrumbsRespVo;
    }
}

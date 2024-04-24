package com.zym.dpan.vo;

import com.zym.dpan.entity.UserFileEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClassName: FolderTreeRespVo
 * Package: com.zym.dpan.vo
 *
 * @Author zym
 * @Create 2024/4/24 17:18
 * @Version 1.0
 */
@Data
public class FolderTreeNodeRespVo {

    /**
     * 子节点
     */
    private List<FolderTreeNodeRespVo> children;

    /**
     * 唯一标识
     */
    private Long id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 父节点id
     */
    private Long parentId;

    public static FolderTreeNodeRespVo setFolderTreeNode(UserFileEntity userFileEntity){
        FolderTreeNodeRespVo folderTreeNode=new FolderTreeNodeRespVo();
        folderTreeNode.setChildren(new ArrayList<FolderTreeNodeRespVo>());
        folderTreeNode.setId(userFileEntity.getFileId());
        folderTreeNode.setLabel(userFileEntity.getFilename());
        folderTreeNode.setParentId(userFileEntity.getParentId());
        return folderTreeNode;
    }

}

package com.zym.dpan.service.impl;
import com.zym.dpan.dao.UserFileDao;
import com.zym.dpan.entity.FileEntity;
import com.zym.dpan.entity.UserFileEntity;
import com.zym.dpan.exception.RRException;
import com.zym.dpan.service.FileService;
import com.zym.dpan.service.UserFileService;
import com.zym.dpan.vo.FileSecUploadVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * ClassName: UserFileServiceImpl
 * Package: com.zym.dpan.service
 *
 * @Author zym
 * @Create 2024/4/7 18:46
 * @Version 1.0
 */
@Service("userFileService")
public class UserFileServiceImpl implements UserFileService {
    @Autowired
    FileService fileService;
    @Autowired
    UserFileDao userFileDao;
    @Override
    public boolean secUpload(FileSecUploadVo fileSecUploadVo) {
        // 1.查询该文件是否已经上传
        FileEntity fileEntity = fileService.selectByIdentifier(fileSecUploadVo.getIdentifier());
        // 2.未上传：过直接返回，后续进行分块上传
        if(fileEntity==null){
            return false;
        }
        // 3.上传过：保存本次文件上传记录
        UserFileEntity userFileEntity = new UserFileEntity();
        userFileEntity.setDefaultValue();
        // filename和fileEntity中的filename是不一样的
        BeanUtils.copyProperties(fileSecUploadVo,userFileEntity);
        userFileEntity.setFileSizeDesc(fileEntity.getFileSizeDesc());
        userFileEntity.setRealFileId(fileEntity.getFileId());
        // TODO:文件类型设置
        saveUserFile(userFileEntity);
        return true;
    }

    public void saveUserFile(UserFileEntity userFileEntity){
        if(userFileDao.insert(userFileEntity)!=1){
            throw new RRException("保存用户文件信息失败");
        };
    }

}

package com.zym.dpan.constant;

/**
 * ClassName: UserFileEnum
 * Package: com.zym.dpan.constant
 *
 * @Author zym
 * @Create 2024/4/7 19:55
 * @Version 1.0
 */
public enum UserFileEnum{
    // 删除标识，文件夹标识
    DEL_FLAG_TRUE(1,"是"),DEL_FLAG_FALSE(0,"否"),
    FOLDER_FLAG_TRUE(1,"是"),FOLDER_FLAG_FASE(0,"否"),
    FILE_TYPE_NONE(0,"无类型");
    private int code;
    private String msg;
    UserFileEnum(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
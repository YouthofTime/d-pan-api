package com.zym.dpan.constant;

/**
 * ClassName: FileConstant
 * Package: com.zym.dpan.constant
 *
 * @Author zym
 * @Create 2024/4/10 14:37
 * @Version 1.0
 */
public class FileConstant {
    public enum FileType {
        // 文件类型
        NORMAL(1, "普通文件"),
        COMPRESSION(2, "压缩文件"),
        EXCEL(3, "Excel 文件"),
        WORD(4, "Word 文件"),
        PDF(5, "PDF 文件"),
        TXT(6, "文本文件"),
        IMAGE(7, "图片文件"),
        AUDIO(8, "音频文件"),
        VIDEO(9, "视频文件"),
        PPT(10, "PPT 文件"),
        SOURCE_CODE(11, "源码文件"),
        CSV(12, "CSV 文件"),
        UNKNOWN(-1, "未知文件类型");
        private Integer code;
        private String msg;

        FileType(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}

package com.zym.dpan.utils;

import com.zym.dpan.constant.FileConstant;
import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * ClassName: FileTypeClassifier
 * Package: com.zym.dpan.utils
 *
 * @Author zym
 * @Create 2024/4/10 16:40
 * @Version 1.0
 */
public class FileTypeClassifier {
    private static final Tika TIKA = new Tika();

    /**
     * 根据文件名获取对应文件类型（分类处理）
     * @param filename
     * @return
     */
    public static FileConstant.FileType classifyFileType(String filename) {
        String mimeType = TIKA.detect(filename);
        if (mimeType.startsWith("image/")) {
            return FileConstant.FileType.IMAGE;
        } else if (mimeType.startsWith("audio/")) {
            return FileConstant.FileType.AUDIO;
        } else if (mimeType.startsWith("video/")) {
            return FileConstant.FileType.VIDEO;
        } else if (mimeType.equals("application/vnd.ms-excel") ||
                mimeType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return FileConstant.FileType.EXCEL;
        } else if (mimeType.equals("application/msword") ||
                mimeType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
            return FileConstant.FileType.WORD;
        } else if (mimeType.equals("application/pdf")) {
            return FileConstant.FileType.PDF;
        } else if (mimeType.equals("text/plain")) {
            return FileConstant.FileType.TXT;
        } else if (mimeType.equals("application/zip") ||
                mimeType.equals("application/x-rar-compressed")) {
            return FileConstant.FileType.COMPRESSION;
        } else if (mimeType.equals("application/vnd.ms-powerpoint") ||
                mimeType.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation")) {
            return FileConstant.FileType.PPT;
        } else if (mimeType.equals("text/csv")) {
            return FileConstant.FileType.CSV;
        } else if (mimeType.startsWith("text/")) {
            return FileConstant.FileType.SOURCE_CODE;
        } else {
            return FileConstant.FileType.NORMAL;
        }
    }
}

package com.zym.dpan.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.UUID;

/**
 * ClassName: FileUtil
 * Package: com.zym.dpan.utils
 *
 * @Author zym
 * @Create 2024/4/8 16:41
 * @Version 1.0
 */
public class FileUtil {


    @Value("${dpan.storage.local.rootFilePath}")
    public static String rootFilePath;
    @Value("${dpan.storage.local.tempPath}")
    public static String tempPath;
    private static final String KB_STR = "K";
    private static final String MB_STR = "M";
    private static final String GB_STR = "G";
    private static final Integer UNIT = 1024;
    private static final String FILE_SIZE_DESC_FORMAT = "%.2f";
    public static final String CHUNKS_FOLDER_NAME = "chunks";
    public static final String COMMON_SEPARATOR = "_";
    private static final Integer ONE_INT = 1;
    private static final Integer MINUS_ONE_INT = -1;
    private static final String EMPTY_STR = "";
    private static final String SLASH = "/";



    /**
     * 将输入流写入文件中
     * @param inputStream:输入流
     * @param targetFile:目标文件
     * @param totalSize:写入字节数
     * @throws IOException
     */
    public static void writeStreamToFile(InputStream inputStream, File targetFile,long totalSize) throws IOException {
        // 创建目标文件（包括其父目录）
        if(!targetFile.getParentFile().exists()){
            boolean mkdirs = targetFile.getParentFile().mkdirs();
        }
        boolean newFile = targetFile.createNewFile();
        // 创建RandomAccessFile实例，从RandomAccessFile中获取文件通道（可读写文件通道）
        RandomAccessFile randomAccessFile = new RandomAccessFile(targetFile,"rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        // 从InputStream获取文件通道（只写通道）
        ReadableByteChannel readableByteChannel = Channels.newChannel(inputStream);
        // 使用transferFrom实现两个通道之间的数据交互（用的阻塞模式，不用处理非正常关闭的情况）
        fileChannel.transferFrom(readableByteChannel,0L,totalSize);
    }

    /**
     * 获取文件后缀
     * @param filename
     * @return
     */
    public static String getFileSuffix(String filename){
        if(StringUtils.isBlank(filename)|| !filename.contains(".")){
            return "";
        }
        return filename.substring(filename.lastIndexOf(".")).toUpperCase();

    }

    /**
     * 临时分片文件存放目录
     * @return
     */
    public static String generateChunksFolderPath(){
        return tempPath + File.separator + CHUNKS_FOLDER_NAME;
    }

    /**
     * 生成临时分片文件路径
     * @param identifier
     * @param chunkNumber
     * @return
     */
    public static String generateChunkFilePath(String identifier,Integer chunkNumber){
        LocalDate currentDate = LocalDate.now();
        return new StringBuilder(generateChunksFolderPath())
                .append(File.separator)
                .append(currentDate.getYear())
                .append(File.separator)
                .append(currentDate.getMonth())
                .append(File.separator)
                .append(currentDate.getDayOfMonth())
                .append(File.separator)
                .append(identifier)
                .append(generateChunkFileName(chunkNumber))
                .toString();
    }

    public static String generateMergeFilePath(String suffix){
        LocalDate currentDate = LocalDate.now();
        return new StringBuilder(rootFilePath)
                .append(File.separator)
                .append(currentDate.getYear())
                .append(File.separator)
                .append(currentDate.getMonth())
                .append(File.separator)
                .append(currentDate.getDayOfMonth())
                .append(File.separator)
                .append(UUIDUtil.getUUID())
                .append(suffix)
                .toString();
    }

    public static String generateChunkFileName(Integer chunkNumber){
        return new StringBuffer(UUIDUtil.getUUID())
                .append(COMMON_SEPARATOR)
                .append(chunkNumber).toString();
    }

    public static File createFile(String filePath) throws IOException {
        if (StringUtils.isNotBlank(filePath)) {
            File file = new File(filePath);
            if (file.exists()) {
                return file;
            }
            createFolder(file.getParent());
            file.createNewFile();
            return file;
        }
        return null;
    }

    public static void createFolder(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }


    public static String getFilename(String filePath) {
        String filename = EMPTY_STR;
        if (StringUtils.isBlank(filePath)) {
            return filename;
        }
        if (filePath.indexOf(File.separator) != MINUS_ONE_INT) {
            filename = filePath.substring(filePath.lastIndexOf(File.separator) + ONE_INT);
        }
        if (filePath.indexOf(SLASH) != MINUS_ONE_INT) {
            filename = filePath.substring(filePath.lastIndexOf(SLASH) + ONE_INT);
        }
        return filename;
    }

    public static String getFileSizeDesc(long size) {
        double fileSize = (double) size;
        String fileSizeSuffix = KB_STR;
        fileSize = fileSize / UNIT;
        if (fileSize >= UNIT) {
            fileSize = fileSize / UNIT;
            fileSizeSuffix = MB_STR;
        }
        if (fileSize >= UNIT) {
            fileSize = fileSize / UNIT;
            fileSizeSuffix = GB_STR;
        }
        return String.format(FILE_SIZE_DESC_FORMAT, fileSize) + fileSizeSuffix;
    }

    /**
     * 获取文件的content-type
     *
     * @param filePath
     * @return
     */
    public static String getContentType(String filePath) {
        Tika tika = new Tika();
        File file = new File(filePath);
        String fileType = null;
        try {
            fileType = tika.detect(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileType;
    }
}

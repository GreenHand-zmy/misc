package org.smart4j.framework.bean;

import java.io.InputStream;

/**
 * 封装上传文件参数
 */
public class FileParam {
    // 上传文件对应的参数名
    private String fieldName;
    // 上传文件名
    private String fileName;
    // 上传文件大小
    private long fileSize;
    // 上传文件类型
    private String contentType;
    // 上传文件的输入流
    private InputStream input;

    public FileParam(String fieldName, String fileName, long fileSize, String contentType, InputStream input) {
        this.fieldName = fieldName;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.input = input;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public InputStream getInput() {
        return input;
    }

    @Override
    public String toString() {
        return "FileParam{" +
                "fieldName='" + fieldName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", contentType='" + contentType + '\'' +
                ", input=" + input +
                '}';
    }
}

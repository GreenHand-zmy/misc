package org.smart4j.framework.helper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.bean.FileParam;
import org.smart4j.framework.bean.NormalParam;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.FileUtil;
import org.smart4j.framework.util.StreamUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 上传助手类
 */
public final class UploadHelper {
    private static final Logger logger = LoggerFactory.getLogger(UploadHelper.class);

    /**
     * 文件上传对象
     */
    private static ServletFileUpload servletFileUpload;

    /**
     * 初始化
     *
     * @param servletContext
     */
    public static void init(ServletContext servletContext) {
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        servletFileUpload = new ServletFileUpload(new DiskFileItemFactory(
                DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository
        ));
        int uploadLimit = ConfigHelper.getAppUploadLimit();
        if (uploadLimit != 0) {
            // 限制最大上传文件大小
            servletFileUpload.setSizeMax(uploadLimit * 1024 * 1024);
        }
    }

    /**
     * 返回请求是否为Multipart类型
     *
     * @param request
     * @return
     */
    public static boolean isMultipart(HttpServletRequest request) {
        return ServletFileUpload.isMultipartContent(request);
    }

    /**
     * 通过http请求创建对象
     *
     * @param request
     * @return
     */
    public static Param createParam(HttpServletRequest request) {
        List<NormalParam> normalParamList = new ArrayList<NormalParam>();
        List<FileParam> fileParamList = new ArrayList<FileParam>();
        try {

            Map<String, List<FileItem>> fileItemListMap = servletFileUpload.parseParameterMap(request);
            if (CollectionUtil.isNotEmpty(fileItemListMap)) {
                for (Map.Entry<String, List<FileItem>> fileItemListEntry : fileItemListMap.entrySet()) {
                    String fieldName = fileItemListEntry.getKey();
                    List<FileItem> fileItemList = fileItemListEntry.getValue();
                    if (CollectionUtil.isNotEmpty(fileItemList)) {
                        for (FileItem fileItem : fileItemList) {
                            if (fileItem.isFormField()) {
                                String fieldValue = fileItem.getString("UTF-8");
                                normalParamList.add(new NormalParam(fieldName, fieldValue));
                            } else {
                                String fileName = fileItem.getName();
                                long fileSize = fileItem.getSize();
                                String contentType = fileItem.getContentType();
                                InputStream inputStream = fileItem.getInputStream();
                                fileParamList.add(new FileParam(fieldName, fileName, fileSize, contentType, inputStream));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("create param failure", e);
            throw new RuntimeException(e);
        }
        return new Param(normalParamList, fileParamList);
    }

    /**
     * 上传文件
     *
     * @param basePath
     * @param fileParam
     */
    public static void uploadFile(String basePath, FileParam fileParam) {
        try {
            if (fileParam != null) {
                // 获取文件上传路径
                String filePath = basePath + fileParam.getFileName();
                // 根据上传路径父级目录
                FileUtil.createFile(filePath);
                // 获取输入流
                InputStream input = new BufferedInputStream(fileParam.getInput());
                // 获取输出流
                OutputStream output = new BufferedOutputStream(new FileOutputStream(filePath));
                // 将输入流传入输出流中去
                StreamUtil.copyStream(input, output);
            }
        } catch (FileNotFoundException e) {
            logger.error("upload file failure", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 批量上传
     *
     * @param basePath
     * @param fileParamList
     */
    public static void uploadFile(String basePath, List<FileParam> fileParamList) {
        for (FileParam fileParam : fileParamList) {
            uploadFile(basePath, fileParam);
        }
    }
}

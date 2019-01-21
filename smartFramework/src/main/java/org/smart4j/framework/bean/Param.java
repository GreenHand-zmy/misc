package org.smart4j.framework.bean;

import org.smart4j.framework.util.CastUtil;
import org.smart4j.framework.util.CollectionUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求参数对象
 */
public class Param {
    // 普通参数
    private List<NormalParam> normalParamList;
    // 上传文件参数
    private List<FileParam> fileParamList;

    public Param(List<NormalParam> normalParamList) {
        this.normalParamList = normalParamList;
    }

    public Param(List<NormalParam> normalParamList, List<FileParam> fileParamList) {
        this.normalParamList = normalParamList;
        this.fileParamList = fileParamList;
    }

    /**
     * 获取普通请求参数映射
     *
     * @return
     */
    public Map<String, Object> getFieldMap() {
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        // 添加普通参数集合到映射中
        if (CollectionUtil.isNotEmpty(normalParamList)) {
            for (NormalParam normalParam : normalParamList) {
                String filedName = normalParam.getFiledName();
                String filedValue = normalParam.getFiledValue();
                // 添加到映射中
                fieldMap.put(filedName, filedValue);
            }
        }
        return fieldMap;
    }

    /**
     * 获取文件请求参数映射
     * 一个参数可能对应多个上传文件
     *
     * @return
     */
    public Map<String, List<FileParam>> getFileMap() {
        Map<String, List<FileParam>> fileMap = new HashMap<String, List<FileParam>>();
        // 遍历文件上传请求(一个请求参数名可能对应多个上传文件)
        for (FileParam fileParam : fileParamList) {
            String filedName = fileParam.getFieldName();
            if (fileMap.containsKey(filedName)) {
                fileMap.get(filedName).add(fileParam);
            } else {
                List<FileParam> fileParams = new ArrayList<FileParam>();
                fileParams.add(fileParam);
                fileMap.put(filedName, fileParams);
            }
        }
        return fileMap;
    }

    /**
     * 返回参数名对应的上传文件(多个)
     *
     * @param fieldName
     * @return
     */
    public List<FileParam> getFileParamList(String fieldName) {
        return getFileMap().get(fieldName);
    }

    /**
     * 返回参数名对应的上传文件(单个)
     *
     * @param fieldName
     * @return
     */
    public FileParam getFileParam(String fieldName) {
        List<FileParam> fileParams = getFileMap().get(fieldName);
        // 上传文件集合不为空,并且集合大小为1
        if (CollectionUtil.isNotEmpty(fileParams) && fileParams.size() == 1) {
            return fileParams.get(0);
        }
        return null;
    }

    /**
     * 根据参数名获取long型参数值
     *
     * @param name 参数名
     * @return
     */
    public long getLong(String name) {
        return CastUtil.castLong(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取String型参数值
     *
     * @param name
     * @return
     */
    public String getString(String name) {
        return CastUtil.castString(getFieldMap().get(name));
    }

    /**
     * 判断参数实体是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return CollectionUtil.isEmpty(fileParamList) && CollectionUtil.isEmpty(normalParamList);
    }
}

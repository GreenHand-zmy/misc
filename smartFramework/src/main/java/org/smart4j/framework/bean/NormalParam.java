package org.smart4j.framework.bean;

/**
 * 普通参数对应于路径上通过Query格式的数据 表单提交的普通数据
 */
public class NormalParam {
    // 参数名
    private String filedName;
    // 参数值
    private String filedValue;

    public NormalParam(String filedName, String filedValue) {
        this.filedName = filedName;
        this.filedValue = filedValue;
    }

    public String getFiledName() {
        return filedName;
    }

    public String getFiledValue() {
        return filedValue;
    }

    @Override
    public String toString() {
        return "NormalParam{" +
                "filedName='" + filedName + '\'' +
                ", filedValue='" + filedValue + '\'' +
                '}';
    }
}

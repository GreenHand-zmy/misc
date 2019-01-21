package org.smart4j.framework.helper;

import org.smart4j.framework.bean.NormalParam;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.util.CodeUtil;
import org.smart4j.framework.util.StreamUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public final class RequestHelp {
    public static Param createParam(HttpServletRequest request) throws IOException {
        List<NormalParam> normalParamList = new ArrayList<NormalParam>();
        normalParamList.addAll(parseParameterName(request));
        normalParamList.addAll(parseInputStream(request));
        return new Param(normalParamList);
    }

    private static List<NormalParam> parseParameterName(HttpServletRequest request) {
        List<NormalParam> normalParamList = new ArrayList<NormalParam>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            // 添加参数和参数值到集合中
            normalParamList.add(new NormalParam(paramName, paramValue));
        }
        return normalParamList;
    }

    private static List<NormalParam> parseInputStream(HttpServletRequest request) throws IOException {
        List<NormalParam> normalParamList = new ArrayList<NormalParam>();
        // 获取post请求的中参数
        String requestBody = CodeUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
        if (!requestBody.equals("")) {
            // 解析出post请求中的参数和参数值,并添加到映射中
            String[] params = requestBody.split("&");
            if (params.length > 0) {
                for (String param : params) {
                    String[] split = param.split("=");
                    String paramName = split[0];
                    String paramValue = split[1];
                    normalParamList.add(new NormalParam(paramName, paramValue));
                }
            }
        }
        return normalParamList;
    }
}

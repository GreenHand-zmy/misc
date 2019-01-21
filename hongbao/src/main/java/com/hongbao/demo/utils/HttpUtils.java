package com.hongbao.demo.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zmy on 2018/5/4.
 */
public final class HttpUtils {
    /**
     * 执行一次Http连接并返回Response
     *
     * @param url      连接的地址
     * @param method   对应http Method
     * @param jsonData 请求的json数据
     * @return 返回的Json Response
     */
    public static String doConnection(String url, String method, String jsonData) {
        try {
            // 创建连接
            URL httPurl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httPurl.openConnection();

            // 设置http连接属性
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);

            // 设置请求方式
            connection.setRequestMethod(method);
            // 设置接收数据的格式
            connection.setRequestProperty("Accept", "application/json");
            // 设置发送数据的格式
            connection.setRequestProperty("Content-Type", "application/json");

            connection.connect();

            // 发送json数据
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            out.write(jsonData);
            out.flush();
            out.close();

            // 获取返回值
            int responseCode = connection.getResponseCode();
            if (!(responseCode == 200 || responseCode == 204)) {
                throw new RuntimeException("出现错误 error code= " + responseCode);
            }
            // 获取json数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                line = new String(line.getBytes(), "utf-8");
                sb.append(line);
            }

            // 关闭连接
            reader.close();
            connection.disconnect();
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取链接中的参数值
     *
     * @param url url地址
     * @param key 参数名
     * @return 参数值
     */
    public static String getParamFromUrl(String url, String key) {

        // 存放url参数映射
        Map<String, String> keyValueMap = new HashMap<>();

        // 解析红包链接参数
        String[] params = url.substring(url.indexOf('?') + 1).split("&");
        for (String param : params) {
            // 将key value以'='分割
            String[] paramKeyValue = param.split("=");
            String xKey = paramKeyValue[0];

            // 如果只有key没有value,存放空的value
            if (paramKeyValue.length < 2) {
                keyValueMap.put(xKey, null);
            }
            // 如果key和value都存在,直接放入map中
            else {
                String value = paramKeyValue[1];
                keyValueMap.put(xKey, value);
            }
        }
        return keyValueMap.get(key);
    }
    public static void main(String[] args){
        String json="{'method':'phone','group_sn':'29f7f34349ac7892','sign':'4be1345e3c08f2ce13205edfa5180744','phone':'','device_id':'','hardware_id':'','platform':4,'track_id':'1528167231|ab727c61946f36800cb00face3ec5fb05814144535555f6456|ae941e37cd9f6f1025c591b923aeee5e','weixin_avatar':'http://thirdqq.qlogo.cn/qqapp/101204453/E705460BABA345A09E3B656F85DD1295/40','weixin_username':'Xander','unionid':'fuck'}";
        String requesturl="https://h5.ele.me/restapi/marketing/promotion/weixin/E705460BABA345A09E3B656F85DD1295";
        String s = HttpUtils.doConnection(requesturl,"POST", json);
        System.out.println(s);
    }
}

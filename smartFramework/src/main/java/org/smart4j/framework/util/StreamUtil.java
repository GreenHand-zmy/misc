package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 处理流的工具类
 */
public final class StreamUtil {
    private static final Logger logger = LoggerFactory.getLogger(StreamUtil.class);

    public static String getString(InputStream input) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            logger.error("get string failure", e);
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    /**
     * 将输入流拷贝到输出流中
     *
     * @param input
     * @param output
     */
    public static void copyStream(InputStream input, OutputStream output) {
        try {
            int length;
            byte[] buffer = new byte[4 * 1024];
            while ((length = input.read(buffer, 0, buffer.length)) != -1) {
                output.write(buffer, 0, length);
            }
            output.flush();
        } catch (IOException e) {
            logger.error("copy stream failure", e);
            throw new RuntimeException(e);
        } finally {
            try {
                input.close();
                output.close();
            } catch (IOException e) {
                logger.error("close stream failure", e);
            }
        }
    }
}

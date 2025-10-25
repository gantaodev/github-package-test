package com.zd.core.util;


import com.zd.core.exception.IORuntimeException;

import java.io.*;

/**
 * io工具类
 *
 * @author gantao
 * @date 2023/04/07
 */
public abstract class IOUtil {

    /**
     * 转换字符
     *
     * @param reader 读者
     * @return {@link String}
     */
    public static String read(Reader reader) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bfr = new BufferedReader(reader)) {
            String line;
            while ((line = bfr.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        return builder.toString();
    }

    /**
     * 读
     *
     * @param inputStreamReader 输入流读者
     * @return {@link String}
     */
    public static String read(InputStream inputStreamReader) {
        return read(new InputStreamReader(inputStreamReader));
    }
}

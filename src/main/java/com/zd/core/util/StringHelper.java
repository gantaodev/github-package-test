package com.zd.core.util;

/**
 * 字符串工具类
 *
 * @author gantao
 * @date 2023/04/05
 */
public abstract class StringHelper {

    /**
     * 字符串
     *
     * @param object 对象
     * @return {@link String}
     */
    public static String toString(Object object) {
        if (object == null) {
            return "";
        }
        return object.toString();
    }
}

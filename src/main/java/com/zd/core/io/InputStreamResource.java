package com.zd.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 输入流资源
 * 文件资源
 *
 * @author gantao
 * @date 2022/11/10
 */
public interface InputStreamResource {

    /**
     * 获取输入流
     *
     * @return {@link InputStream}
     */
    InputStream getInputStream() throws IOException;

}

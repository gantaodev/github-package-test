package com.zd.core.resource;

import java.io.IOException;

/**
 * 文件资源位置
 *
 * @author gantao
 * @date 2022/11/10
 */
public interface FileResourceLocation {

    /**
     * 得到文件资源
     *
     * @param resource 资源
     * @return {@link FileResource}
     * @throws IOException 在一般解析/读取失败的情况下
     */
    FileResource getFileResource(String resource) throws IOException;
}

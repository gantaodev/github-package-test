package com.zd.core.util;


import com.zd.core.resource.DefaultFileResourceLocation;
import com.zd.core.resource.FileResource;
import com.zd.core.resource.FileResourceLocation;

import java.io.IOException;

/**
 * 资源位置辅助
 *
 * @author gantao
 * @date 2022/11/10
 */
public abstract class FileResourceLocationHelper {

    /**
     * 文件资源位置
     */
    private static final FileResourceLocation FILE_RESOURCE_LOCATION;

    static {
        String resourceRoot = System.getProperty("pro.config.dir", "config");
        FILE_RESOURCE_LOCATION = new DefaultFileResourceLocation(resourceRoot);
    }

    /**
     * 得到文件资源
     *
     * @param resource 资源
     * @return {@link FileResource}
     * @throws IOException 在一般解析/读取失败的情况下
     */
    public static FileResource getFileResource(String resource) throws IOException {
        return FILE_RESOURCE_LOCATION.getFileResource(resource);
    }
}

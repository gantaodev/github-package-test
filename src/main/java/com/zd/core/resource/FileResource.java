package com.zd.core.resource;

import com.zd.core.io.InputStreamResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件资源
 *
 * @author gantao
 * @date 2022/11/10
 */
public class FileResource {
    /**
     * 文件资源位置类型
     */
    private final FileResourceType fileResourceType;
    /**
     * 路径
     */
    private final String path;
    /**
     * 输入流资源
     */
    private final InputStreamResource inputStreamResource;

    public FileResource(String path, InputStreamResource inputStreamResource, FileResourceType type) {
        this.path = path;
        this.fileResourceType = type;
        this.inputStreamResource = inputStreamResource;
    }

    public String getPath() {
        return path;
    }

    public FileResourceType getFileResourceType() {
        return fileResourceType;
    }

    public InputStream getInputStream() throws IOException {
        return inputStreamResource.getInputStream();
    }

    @Override
    public String toString() {
        return "type = '" + fileResourceType + "', " + "path = '" + path + "'";
    }


}

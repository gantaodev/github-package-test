package com.zd.core.resource;

import com.zd.core.io.SpringInputStreamSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * 默认文件资源位置
 *
 * @author gantao
 * @date 2022/11/10
 */
public class DefaultFileResourceLocation implements FileResourceLocation {

    private static final Logger log = LoggerFactory.getLogger(DefaultFileResourceLocation.class);
    /**
     * 资源根
     */
    private final String resourceRoot;

    public DefaultFileResourceLocation(String resourceRoot) {
        Objects.requireNonNull(resourceRoot, "resourceRoot cannot be null");
        this.resourceRoot = resourceRoot;
    }

    /**
     * 得到文件资源, 获取顺序：
     * 相对路径{@link #resourceRoot}目录下资源 >  classpath下资源 > 磁盘资源 > classpath{@link #resourceRoot}目录下资源
     *
     * @param resource 资源
     * @return {@link FileResource}
     */
    @Override
    public FileResource getFileResource(String resource) throws IOException {
        Objects.requireNonNull(resource, "Resource cannot be null");

        boolean absolute = Paths.get(resource).isAbsolute();
        if (absolute) {
            FileSystemResource fileSystemResource = new FileSystemResource(resource);
            if (fileSystemResource.exists()) {
                return new FileResource(
                        resource, new SpringInputStreamSource(fileSystemResource), FileResourceType.FILE_SYSTEM);
            }
        }

        FileSystemResource userDirFileSystemResource = new FileSystemResource(
                Paths.get(System.getProperty("user.dir"), resourceRoot, resource));
        if (userDirFileSystemResource.exists()) {
            String path = userDirFileSystemResource.getPath();
            logMessage(path, FileResourceType.FILE_SYSTEM);
            return new FileResource(
                    path, new SpringInputStreamSource(userDirFileSystemResource), FileResourceType.FILE_SYSTEM);
        }

        ClassPathResource classPathResource = new ClassPathResource(resource);
        if (classPathResource.exists()) {
            String path = classPathResource.getPath();
            logMessage(path, FileResourceType.CLASS_PATH);
            return new FileResource(
                    path, new SpringInputStreamSource(classPathResource), FileResourceType.CLASS_PATH);
        }

        ClassPathResource rootClassPathResource = new ClassPathResource(Paths.get(resourceRoot, resource).toString());
        if (rootClassPathResource.exists()) {
            String path = rootClassPathResource.getPath();
            logMessage(path, FileResourceType.CLASS_PATH);
            return new FileResource(
                    path, new SpringInputStreamSource(rootClassPathResource), FileResourceType.CLASS_PATH);
        }

        String description = "resource ['" + resource + "']";
        throw new FileNotFoundException(description +
                " Cannot get the file resource because it does not exist");

    }

    /**
     * 日志消息
     *
     * @param path 路径
     * @param type 类型
     */
    private void logMessage(String path, FileResourceType type) {
        if (log.isDebugEnabled()) {
            log.debug("{} : {}", type, path);
        }
    }
}

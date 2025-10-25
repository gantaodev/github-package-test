package com.zd.core.initialize;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import com.zd.core.log.LogbackConfigInitException;
import com.zd.core.resource.FileResource;
import com.zd.core.resource.FileResourceLocation;
import com.zd.core.resource.FileResourceType;
import com.zd.core.util.FileResourceLocationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

import java.io.File;
import java.io.IOException;

/**
 * logback配置初始化
 *
 * @author gantao
 * @date 2022/11/10
 */
public class LogbackConfigInit implements ApplicationInitialization {

    private static final Logger log = LoggerFactory.getLogger(LogbackConfigInit.class);
    @Nullable
    private FileResourceLocation fileResourceLocation;

    @Override
    public void initialize() {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        FileResource fileResource;
        try {
            fileResource = getFileResource("logback.xml");
        } catch (IOException e) {
            log.error("{}", "忽略异常, 使用默认配置", e);
            return;
        }
        if (fileResource == null
                // 不处理classpath下的配置文件, 日志框架会自己加载
                || FileResourceType.CLASS_PATH.equals(fileResource.getFileResourceType())) {
            return;
        }
        if (log.isDebugEnabled()) {
            log.debug("重新加载 logback 配置文件: {}", fileResource);
        }
        String externalConfigFileLocation = fileResource.getPath();
        File externalConfigFile = new File(fileResource.getPath());
        if (!externalConfigFile.exists()) {
            throw new IllegalArgumentException("Logback External Config File Parameter does not reference a file that exists");
        }
        if (!externalConfigFile.isFile()) {
            throw new IllegalStateException("Logback External Config File Parameter exists, but does not reference a file");
        }
        if (!externalConfigFile.canRead()) {
            throw new IllegalStateException("Logback External Config File exists and is a file, but cannot be read.");
        }
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        try {
            configurator.doConfigure(externalConfigFileLocation);
        } catch (JoranException e) {
            throw new LogbackConfigInitException(e);
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
    }


    /**
     * 获取文件资源
     * @param resource 资源名称
     * @return {@link FileResource}
     * @throws IOException
     */
    protected FileResource getFileResource(String resource) throws IOException {
        if (fileResourceLocation != null) {
            return fileResourceLocation.getFileResource(resource);
        } else {
            return FileResourceLocationHelper.getFileResource(resource);
        }
    }

    public void setFileResourceLocation(@Nullable FileResourceLocation fileResourceLocation) {
        this.fileResourceLocation = fileResourceLocation;
    }
}

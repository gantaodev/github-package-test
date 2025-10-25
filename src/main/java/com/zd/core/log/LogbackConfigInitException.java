package com.zd.core.log;

import com.zd.core.initialize.ApplicationInitializationException;

/**
 * logback配置初始化异常
 *
 * @author gantao
 * @date 2022/11/10
 */
public class LogbackConfigInitException extends ApplicationInitializationException {

    public LogbackConfigInitException() {
        super();
    }

    public LogbackConfigInitException(String message) {
        super(message);
    }

    public LogbackConfigInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogbackConfigInitException(Throwable cause) {
        super(cause);
    }

}

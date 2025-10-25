package com.zd.core.initialize;

/**
 * 应用程序初始化异常
 *
 * @author gantao
 * @date 2022/11/10
 */
public class ApplicationInitializationException extends RuntimeException {
    public ApplicationInitializationException() {
        super();
    }

    public ApplicationInitializationException(String message) {
        super(message);
    }

    public ApplicationInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationInitializationException(Throwable cause) {
        super(cause);
    }

}

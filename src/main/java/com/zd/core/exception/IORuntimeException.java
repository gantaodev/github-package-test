package com.zd.core.exception;

/**
 * io运行时异常
 *
 * @author gantao
 * @date 2023/04/07
 */
public class IORuntimeException extends RuntimeException {

    public IORuntimeException() {
        super();
    }


    public IORuntimeException(String message) {
        super(message);
    }

    public IORuntimeException(String message, Throwable cause) {
        super(message, cause);
    }


    public IORuntimeException(Throwable cause) {
        super(cause);
    }

    protected IORuntimeException(String message, Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

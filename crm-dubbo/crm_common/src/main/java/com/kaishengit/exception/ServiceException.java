package com.kaishengit.exception;

public class ServiceException extends RuntimeException {
    /**
     * 定义服务异常
     */
    public ServiceException(){}
    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(String message,Throwable th) {
        super(message,th);
    }

}

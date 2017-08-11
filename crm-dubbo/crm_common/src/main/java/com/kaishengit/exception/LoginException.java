package com.kaishengit.exception;

public class LoginException extends ServiceException {

    /**
     * 定义登录异常
     */
    public LoginException(){}
    public LoginException(String message) {
        super(message);
    }
    public LoginException(String message,Throwable th) {
        super(message,th);
    }
}

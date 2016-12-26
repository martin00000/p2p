package com.eloan.base.Exception;

/**
 * 登录注册业务
 *
 * @author Dingying
 * @create 2016-12-25 23:24
 **/
public class LogicException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /*

     */
    private Integer errCode;

    public Integer getErrCode(){
        return errCode;
    }

    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogicException(String message) {
        super(message);
    }

    public LogicException(String message, Integer errCode) {
        super(message);
        this.errCode = errCode;
    }
}

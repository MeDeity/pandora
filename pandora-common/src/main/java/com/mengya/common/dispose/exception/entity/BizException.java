package com.mengya.common.dispose.exception.entity;

import lombok.Data;

@Data
public class BizException extends RuntimeException {
    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param errorMsg the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BizException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public BizException(BaseErrorCode error) {
        super(error.getMessage());
        this.errorCode = error.getCode();
        this.errorMsg = error.getMessage();
    }


}

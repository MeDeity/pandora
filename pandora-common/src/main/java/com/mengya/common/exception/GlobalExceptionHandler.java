package com.mengya.common.exception;

import com.mengya.common.exception.entity.BizException;
import com.mengya.common.exception.entity.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     */
    @ExceptionHandler(value = BizException.class)
    public ErrorCode bizExceptionHandler(BizException e){
        log.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return ErrorCode.SERVER_ERROR;
    }

    /**
     * 处理空指针的异常
     */
    @ExceptionHandler(value =NullPointerException.class)
    public ErrorCode exceptionHandler(NullPointerException e){
        log.error("发生空指针异常！原因是:",e);
        return ErrorCode.NULL_EXCEPTION;
    }


    /**
     * 处理其他异常
     */
    @ExceptionHandler(value =Exception.class)
    public ErrorCode exceptionHandler(Exception e){
        log.error("未知异常！原因是:",e);
        return ErrorCode.UNKNOW_ERROR;
    }
}

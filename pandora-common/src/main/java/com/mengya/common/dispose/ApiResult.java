package com.mengya.common.dispose;

import com.mengya.common.dispose.exception.entity.BaseErrorCode;
import com.mengya.common.dispose.exception.entity.CommonErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult<T> implements Serializable {
    /**
     * 响应码
     */
    private String code;

    /**
     * 请求是否成功
     */
    private boolean success;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;


    public static ApiResult success(){
        ApiResult result = new ApiResult();
        result.success = true;
        return result;
    }

    public static ApiResult success(Object data){
        ApiResult result = new ApiResult();
        result.success = true;
        result.data = data;
        return result;
    }

    public static ApiResult success(String code,String message,Object data){
        ApiResult result = new ApiResult();
        result.success = true;
        result.code = code;
        result.message = message;
        result.data = data;
        return result;
    }

    public static ApiResult ofFail(BaseErrorCode baseErrorCode) {
        ApiResult result = new ApiResult();
        result.success = false;
        result.code = baseErrorCode.getCode();
        result.message = baseErrorCode.getMessage();
        return result;
    }

    public static ApiResult ofFail(String code, String msg) {
        ApiResult result = new ApiResult();
        result.success = false;
        result.code = code;
        result.message = msg;
        return result;
    }

    public static ApiResult ofFail(String code, String msg, Object data) {
        ApiResult result = new ApiResult();
        result.success = false;
        result.code = code;
        result.message = msg;
        result.setData(data);
        return result;
    }

    public static ApiResult ofFail(CommonErrorCode resultEnum) {
        ApiResult result = new ApiResult();
        result.success = false;
        result.code = resultEnum.getCode();
        result.message = resultEnum.getMessage();
        return result;
    }


}

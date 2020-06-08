package com.mengya.common.exception.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum ErrorCode {
    SUCCESS(200, "成功"),
    NO_PERMISSION(211, "权限不足"),
    SERVER_ERROR(10000, "服务器异常"),
    AUTH_ERROR(10001, "认证失败"),
    PARAMS_ERROR(10002, "参数错误"),
    JSON_PARSE_ERROR(10003, "Json解析错误"),
    ILLEAGAL_STRING(15001, "非法字符串"),
    NULL_EXCEPTION(15002, "空指针异常"),
    UNKNOW_ERROR(16000, "未知错误");

    @Getter
    private int code;

    @Getter
    private String msg;
}

package com.volunteer.util;


import lombok.Getter;

/**
 * 返回状态码枚举
 */
@Getter
public enum ResultEnum {
    //    成功
    SUCCESS_MESSAGE(200, "success"),
    //    无权限
    NO_PERMISSIONS(401, "user have no permission"),
    /**
     *
     */
    USERNAME_OR_PASSWORD_WRONG(403, "账号或者密码错误"),
    //    系统内部错误
    SERVER_HAVE_ERROR(500, "server have some error,please try again"),
    //    参数错误
    PARAM_ERROR(400, "params have some errors, please check and try again");


    private Integer code;

    private String message;

    ResultEnum(Integer s, String success) {
        this.code = s;
        this.message = success;
    }

}

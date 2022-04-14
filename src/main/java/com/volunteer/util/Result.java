package com.volunteer.util;

import lombok.Data;

/**
 * 返回结果包装类
 */
@Data
public class Result {
    private Integer code;

    private String message;

    private Object data;

    public Result(ResultEnum resultEnum,Object data){
        this.code=resultEnum.getCode();
        this.message=resultEnum.getMessage();
        this.data=data;
    }

    public Result(Integer code,String message,Object data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    public static Result createResult(ResultEnum resultEnum,Object data){
        return new Result(resultEnum,data);
    }
}

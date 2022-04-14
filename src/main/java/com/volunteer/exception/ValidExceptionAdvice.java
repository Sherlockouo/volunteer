package com.volunteer.exception;

import com.volunteer.util.Result;
import com.volunteer.util.ResultEnum;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

/**
 * 全局异常控制器，此处主要防止的异常为SpecialComSecVo属性校验时，如果不满足正则表达式则会抛出异常，此处进行处理，即此处处理字段非法的情况
 */
@RestControllerAdvice
public class ValidExceptionAdvice {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public Result handleValidationExceptions(BindException ex){
        Set<String> paramList=new HashSet<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError)error).getField();
            paramList.add(fieldName);
        });
        return Result.createResult(ResultEnum.PARAM_ERROR,paramList);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public Result handleRuntimeException(RuntimeException e){
        return new Result(500,e.getMessage(),null);
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public Result handleDuplicationException(RuntimeException e){
        return new Result(400,e.getMessage(),null);
    }


}

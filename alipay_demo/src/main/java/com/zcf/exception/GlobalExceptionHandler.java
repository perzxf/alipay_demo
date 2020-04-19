package com.zcf.exception;

import com.zcf.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangchuanfei
 * @create 2020-04-19-10:23
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public Result customException(HttpServletRequest request, CustomException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

}

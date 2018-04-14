package com.baegopa.account.commons;


import com.baegopa.account.models.MessageCode;
import com.baegopa.account.models.response.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@EnableWebMvc
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(Throwable.class)
    public CommonResponse handleControllerException(Throwable ex) {
        return new CommonResponse<>(MessageCode.FAIL, ex.toString());
    }
}

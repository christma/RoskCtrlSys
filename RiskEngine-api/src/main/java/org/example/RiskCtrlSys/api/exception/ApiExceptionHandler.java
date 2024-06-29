package org.example.RiskCtrlSys.api.exception;

import org.example.RiskCtrlSys.commons.exception.custom.RedisException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = RedisException.class)
    public void RedisExceptionHandler(){}

}

package com.gree.exception;

import com.gree.result.ResultBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String handle(Exception e) {
        if (e instanceof KellyException) {
            KellyException kellyException = (KellyException)e;
            return ResultBody.error(kellyException.getResponseInfo());
        } else {
            log.error("【系统异常】{}", e);
            return ResultBody.error("【系统异常】" + e.getMessage());
        }
    }
}

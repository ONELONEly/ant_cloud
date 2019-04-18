package com.gree.handle;

import com.gree.exception.KellyException;
import com.gree.result.ResultBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String,Object> handle(Exception e) {
        if (e instanceof KellyException) {
            KellyException kellyException = (KellyException)e;
            return ResultBody.errorMap(kellyException.getMessage());
        } else {
            log.error("【系统异常】{}", e.getCause().getMessage());
            return ResultBody.errorMap("【系统异常】" + e.getCause().getMessage());
        }
    }
}

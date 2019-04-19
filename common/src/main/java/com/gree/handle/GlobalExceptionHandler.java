package com.gree.handle;

import com.gree.exception.KellyException;
import com.gree.result.RestErrorResponse;
import com.gree.result.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestResponse<String> handle(Exception e) {
        if (e instanceof KellyException) {
            KellyException kellyException = (KellyException)e;
            return new RestResponse<>("",new RestErrorResponse(kellyException.getCode(),kellyException.getMessage(),kellyException.getStackTrace(),new Date(),kellyException.getClass().getName()));
        } else {
            log.error("【系统异常】{}", e.getMessage());
            return new RestResponse<>("",new RestErrorResponse(500,e.getCause() == null ? e.getMessage():e.getCause().getMessage(),e.getStackTrace(),new Date(),e.getClass().getName()));
        }
    }
}

package com.gree.handle;

import com.gree.exception.KellyException;
import com.gree.result.RestErrorResponse;
import com.gree.result.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestResponse handle(Exception e) {
        RestErrorResponse errorResponse;
        if (e instanceof KellyException) {
            KellyException kellyException = (KellyException)e;
            errorResponse = new RestErrorResponse(kellyException.getCode(),kellyException.getMessage(),kellyException.getStackTrace(),new Date(),kellyException.getClass().getName());
        } else {
            errorResponse = new RestErrorResponse("500",e.getCause() == null ? e.getMessage():e.getCause().getMessage(),e.getStackTrace(),new Date(),e.getClass().getName());
        }
        log.error("【系统异常】{}", e.getMessage());
        return new RestResponse<>().error(errorResponse.getErrorCode(),errorResponse.getErrorMsg(),errorResponse);
    }
}

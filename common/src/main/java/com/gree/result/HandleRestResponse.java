package com.gree.result;

import com.gree.exception.KellyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class HandleRestResponse<T> implements Serializable {
    private static final long serialVersionUID = -1769325259689007824L;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Class<?> eleType;

    public HandleRestResponse(Class<?> eleType) {
        this.eleType = eleType;
    }

    @SuppressWarnings("unchecked")
    public T handle(RestResponse<?> restResponse){
        RestErrorResponse errorResponse = restResponse.getErrorResponse();
        if(errorResponse != null) {
            throw new KellyException(errorResponse.getErrorMsg(), errorResponse.getErrorCode(), errorResponse.getErrorDate(), errorResponse.getErrorType());
        }else if(restResponse.getData() == null){
            throw new KellyException(ResponseInfoEnum.EMPTY_RESULT);
        }else if(restResponse.getData().getClass() != eleType){
            throw new KellyException(ResponseInfoEnum.JSON_CONVERT_ERROR);
        }
        return (T)restResponse.getData();
    }
}

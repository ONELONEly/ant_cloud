package com.gree.result;

import com.alibaba.fastjson.JSON;
import com.gree.exception.KellyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;

public class HandleRestResponse<T> implements Serializable {
    private static final long serialVersionUID = -1769325259689007824L;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public HandleRestResponse() {
    }

    @SuppressWarnings("unchecked")
    public T handle(Class<T> eleType,RestResponse<?> restResponse){
        logger.debug("rest:{}", JSON.toJSONString(restResponse));
        RestErrorResponse errorResponse = restResponse.getErrorResponse();
        if(errorResponse != null) {
            throw new KellyException(errorResponse.getErrorMsg(), errorResponse.getErrorCode(), errorResponse.getErrorDate(), errorResponse.getErrorType());
        }else if(restResponse.getData() == null){
            throw new KellyException(ResponseInfoEnum.EMPTY_RESULT,new Date(),"KellyException");
        }else if(restResponse.getData().getClass() != eleType){
            logger.debug("classType:{}",restResponse.getData().getClass());
            throw new KellyException(ResponseInfoEnum.JSON_CONVERT_ERROR,new Date(),"KellyException");
        }
        return (T)restResponse.getData();
    }
}

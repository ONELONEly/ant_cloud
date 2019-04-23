package com.gree.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class KellyMetaObjectInject implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        Object field = getFieldValByName("MODIFY_DATE",metaObject);
        if(field == null){
            setFieldValByName("modifyDate", LocalDateTime.now(),metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object field = getFieldValByName("modifyDate",metaObject);
        if(field == null){
            setFieldValByName("modifyDate", LocalDateTime.now(),metaObject);
        }
    }
}

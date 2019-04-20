package com.gree.entity;

import java.io.Serializable;

/**
 * 自定义实体父类 ， 这里可以放一些公共字段信息
 * VO view object 视图对象，用于展示层，把某个页面或组件的数据封装起来
 * DTO Data transfer object 数据传输对象，展示层与服务层的数据传输对象
 * DO domain object 领域对象，业务实体
 * BO bussines objevt 业务对象
 * PO 持久化对象，与数据库的数据结构一一形成对应
 */
public class SuperEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

package com.gree.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class MyPage<T> extends Page<T> {

    private static final long serialVersionUID = 957934291473634189L;

    private Integer selectInt;
    private String selectString;

    public MyPage(long current, long size) {
        super(current, size);
    }

    public Integer getSelectInt() {
        return selectInt;
    }

    public void setSelectInt(Integer selectInt) {
        this.selectInt = selectInt;
    }

    public String getSelectString() {
        return selectString;
    }

    public void setSelectString(String selectString) {
        this.selectString = selectString;
    }
}

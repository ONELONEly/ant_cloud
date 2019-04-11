package com.gree.entity.vo;

import java.io.Serializable;

public class Permission implements Serializable {

    private String url;

    public Permission() {
    }

    public Permission(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

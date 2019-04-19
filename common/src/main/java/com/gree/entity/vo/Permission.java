package com.gree.entity.vo;

import java.io.Serializable;

public class Permission implements Serializable {

    private static final long serialVersionUID = 2108124620532175208L;
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

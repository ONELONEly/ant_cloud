package com.gree.redis.entity;

import java.io.Serializable;

public class User implements Serializable{

    private String usid;
    private String dsca;
    private String pawd;

    public User() {
    }

    public User(String usid, String dsca, String pawd) {
        this.usid = usid;
        this.dsca = dsca;
        this.pawd = pawd;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getDsca() {
        return dsca;
    }

    public void setDsca(String dsca) {
        this.dsca = dsca;
    }

    public String getPawd() {
        return pawd;
    }

    public void setPawd(String pawd) {
        this.pawd = pawd;
    }
}

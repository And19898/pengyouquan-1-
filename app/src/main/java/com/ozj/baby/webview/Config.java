package com.ozj.baby.webview;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class Config extends BmobObject implements Serializable {
    private static final long serialVersionUID = -6330867314341324839L;
    private String url;
    private String appid;
    private String type;
    private boolean show;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    @Override
    public String toString() {
        return "Config{" +
                "url='" + url + '\'' +
                ", appid='" + appid + '\'' +
                ", type='" + type + '\'' +
                ", show=" + show +
                '}';
    }
}

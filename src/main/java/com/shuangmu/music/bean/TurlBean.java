package com.shuangmu.music.bean;

/**
 * Created by Lin on 2018/4/13.
 */
public class TurlBean {

    private String url_short;
    private String url_long;
    private int type;
    public void setUrl_short(String url_short) {
        this.url_short = url_short;
    }
    public String getUrl_short() {
        return url_short;
    }

    public void setUrl_long(String url_long) {
        this.url_long = url_long;
    }
    public String getUrl_long() {
        return url_long;
    }

    public void setType(int type) {
        this.type = type;
    }
    public int getType() {
        return type;
    }
}

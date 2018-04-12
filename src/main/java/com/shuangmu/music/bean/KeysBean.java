/**
  * Copyright 2018 bejson.com 
  */
package com.shuangmu.music.bean;

public class KeysBean {

    private int code;
    private long cid;
    private String userip;
    private Data data;
    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

    public void setCid(long cid) {
         this.cid = cid;
     }
     public long getCid() {
         return cid;
     }

    public void setUserip(String userip) {
         this.userip = userip;
     }
     public String getUserip() {
         return userip;
     }

    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }
	@Override
	public String toString() {
		return "KeysBean [code=" + code + ", cid=" + cid + ", userip=" + userip + ", data=" + data + "]";
	}

}
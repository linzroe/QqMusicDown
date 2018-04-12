/**
  * Copyright 2018 bejson.com 
  */
package com.shuangmu.music.bean;

public class Items {

    private int subcode;
    private String songmid;
    private String filename;
    private String vkey;
    public void setSubcode(int subcode) {
         this.subcode = subcode;
     }
     public int getSubcode() {
         return subcode;
     }

    public void setSongmid(String songmid) {
         this.songmid = songmid;
     }
     public String getSongmid() {
         return songmid;
     }

    public void setFilename(String filename) {
         this.filename = filename;
     }
     public String getFilename() {
         return filename;
     }

    public void setVkey(String vkey) {
         this.vkey = vkey;
     }
     public String getVkey() {
         return vkey;
     }
	@Override
	public String toString() {
		return "Items [subcode=" + subcode + ", songmid=" + songmid + ", filename=" + filename + ", vkey=" + vkey + "]";
	}
     
}
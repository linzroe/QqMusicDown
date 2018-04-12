/**
  * Copyright 2018 bejson.com 
  */
package com.shuangmu.music.bean;

/**
 * Auto-generated: 2018-04-12 11:55:8
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Mv {

    private int id;
    private String vid;
    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setVid(String vid) {
         this.vid = vid;
     }
     public String getVid() {
         return vid;
     }

    @Override
    public String
    toString() {
        return "Mv{" +
                "id=" + id +
                ", vid='" + vid + '\'' +
                '}';
    }
}
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
public class Ksong {

    private long id;
    private String mid;
    public void setId(long id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setMid(String mid) {
         this.mid = mid;
     }
     public String getMid() {
         return mid;
     }

    @Override
    public String toString() {
        return "Ksong{" +
                "id=" + id +
                ", mid='" + mid + '\'' +
                '}';
    }
}
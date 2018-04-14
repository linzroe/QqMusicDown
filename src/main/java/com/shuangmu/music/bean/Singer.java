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
public class Singer {

    private long id;
    private String mid;
    private String name;
    private String title;
    private String title_hilight;
    private String type;
    private String uin;
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

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setTitle_hilight(String title_hilight) {
         this.title_hilight = title_hilight;
     }
     public String getTitle_hilight() {
         return title_hilight;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setUin(String uin) {
         this.uin = uin;
     }
     public String getUin() {
         return uin;
     }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", mid='" + mid + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", title_hilight='" + title_hilight + '\'' +
                ", type=" + type +
                ", uin=" + uin +
                '}';
    }
}
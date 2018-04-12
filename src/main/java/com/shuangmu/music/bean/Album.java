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
public class Album {

    private long id;
    private String mid;
    private String name;
    private String subtitle;
    private String title;
    private String title_hilight;
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

    public void setSubtitle(String subtitle) {
         this.subtitle = subtitle;
     }
     public String getSubtitle() {
         return subtitle;
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

    @Override
    public String
    toString() {
        return "Album{" +
                "id=" + id +
                ", mid='" + mid + '\'' +
                ", name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", title='" + title + '\'' +
                ", title_hilight='" + title_hilight + '\'' +
                '}';
    }
}
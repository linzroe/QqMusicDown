/**
  * Copyright 2018 bejson.com 
  */
package com.shuangmu.music.bean;
import java.util.List;

/**
 * Auto-generated: 2018-04-12 11:55:8
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Semantic {

    private int curnum;
    private int curpage;
    private List<String> lists;
    private int totalnum;
    public void setCurnum(int curnum) {
         this.curnum = curnum;
     }
     public int getCurnum() {
         return curnum;
     }

    public void setCurpage(int curpage) {
         this.curpage = curpage;
     }
     public int getCurpage() {
         return curpage;
     }

    public void setLists(List<String> lists) {
         this.lists = lists;
     }
     public List<String> getLists() {
         return lists;
     }

    public void setTotalnum(int totalnum) {
         this.totalnum = totalnum;
     }
     public int getTotalnum() {
         return totalnum;
     }

}
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
public class Data {

    private String keyword;
    private int priority;
    private List<String> qc;
    private Semantic semantic;
    private Song song;
    private List<String> taglists;
    private int totaltime;
    private Zhida zhida;
    public void setKeyword(String keyword) {
         this.keyword = keyword;
     }
     public String getKeyword() {
         return keyword;
     }

    public void setPriority(int priority) {
         this.priority = priority;
     }
     public int getPriority() {
         return priority;
     }

    public void setQc(List<String> qc) {
         this.qc = qc;
     }
     public List<String> getQc() {
         return qc;
     }

    public void setSemantic(Semantic semantic) {
         this.semantic = semantic;
     }
     public Semantic getSemantic() {
         return semantic;
     }

    public void setSong(Song song) {
         this.song = song;
     }
     public Song getSong() {
         return song;
     }

    public void setTaglists(List<String> taglists) {
         this.taglists = taglists;
     }
     public List<String> getTaglists() {
         return taglists;
     }

    public void setTotaltime(int totaltime) {
         this.totaltime = totaltime;
     }
     public int getTotaltime() {
         return totaltime;
     }

    public void setZhida(Zhida zhida) {
         this.zhida = zhida;
     }
     public Zhida getZhida() {
         return zhida;
     }

}
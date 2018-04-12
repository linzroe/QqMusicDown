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
public class File {

    private String media_mid;
    private long size_128;
    private long size_320;
    private long size_aac;
    private long size_ape;
    private int size_dts;
    private long size_flac;
    private long size_ogg;
    private int size_try;
    private String strMediaMid;
    private int try_begin;
    private int try_end;
    public void setMedia_mid(String media_mid) {
         this.media_mid = media_mid;
     }
     public String getMedia_mid() {
         return media_mid;
     }

    public void setSize_128(long size_128) {
         this.size_128 = size_128;
     }
     public long getSize_128() {
         return size_128;
     }

    public void setSize_320(long size_320) {
         this.size_320 = size_320;
     }
     public long getSize_320() {
         return size_320;
     }

    public void setSize_aac(long size_aac) {
         this.size_aac = size_aac;
     }
     public long getSize_aac() {
         return size_aac;
     }

    public void setSize_ape(long size_ape) {
         this.size_ape = size_ape;
     }
     public long getSize_ape() {
         return size_ape;
     }

    public void setSize_dts(int size_dts) {
         this.size_dts = size_dts;
     }
     public int getSize_dts() {
         return size_dts;
     }

    public void setSize_flac(long size_flac) {
         this.size_flac = size_flac;
     }
     public long getSize_flac() {
         return size_flac;
     }

    public void setSize_ogg(long size_ogg) {
         this.size_ogg = size_ogg;
     }
     public long getSize_ogg() {
         return size_ogg;
     }

    public void setSize_try(int size_try) {
         this.size_try = size_try;
     }
     public int getSize_try() {
         return size_try;
     }

    public void setStrMediaMid(String strMediaMid) {
         this.strMediaMid = strMediaMid;
     }
     public String getStrMediaMid() {
         return strMediaMid;
     }

    public void setTry_begin(int try_begin) {
         this.try_begin = try_begin;
     }
     public int getTry_begin() {
         return try_begin;
     }

    public void setTry_end(int try_end) {
         this.try_end = try_end;
     }
     public int getTry_end() {
         return try_end;
     }

    @Override
    public String toString() {
        return "File{" +
                "media_mid='" + media_mid + '\'' +
                ", size_128=" + size_128 +
                ", size_320=" + size_320 +
                ", size_aac=" + size_aac +
                ", size_ape=" + size_ape +
                ", size_dts=" + size_dts +
                ", size_flac=" + size_flac +
                ", size_ogg=" + size_ogg +
                ", size_try=" + size_try +
                ", strMediaMid='" + strMediaMid + '\'' +
                ", try_begin=" + try_begin +
                ", try_end=" + try_end +
                '}';
    }
}
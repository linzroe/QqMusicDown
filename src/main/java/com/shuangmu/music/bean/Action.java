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
public class Action {

    private int alert;
    private int icons;
    private int msg;
    private int tswitch;
    public void setAlert(int alert) {
         this.alert = alert;
     }
     public int getAlert() {
         return alert;
     }

    public void setIcons(int icons) {
         this.icons = icons;
     }
     public int getIcons() {
         return icons;
     }

    public void setMsg(int msg) {
         this.msg = msg;
     }
     public int getMsg() {
         return msg;
     }

    public void setTswitch(int tswitch) {
         this.tswitch = tswitch;
     }
     public int getTswitch() {
         return tswitch;
     }

    @Override
    public String
    toString() {
        return "Action{" +
                "alert=" + alert +
                ", icons=" + icons +
                ", msg=" + msg +
                ", tswitch=" + tswitch +
                '}';
    }
}
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
public class Volume {

    private double gain;
    private double lra;
    private int peak;
    public void setGain(double gain) {
         this.gain = gain;
     }
     public double getGain() {
         return gain;
     }

    public void setLra(double lra) {
         this.lra = lra;
     }
     public double getLra() {
         return lra;
     }

    public void setPeak(int peak) {
         this.peak = peak;
     }
     public int getPeak() {
         return peak;
     }

    @Override
    public String
    toString() {
        return "Volume{" +
                "gain=" + gain +
                ", lra=" + lra +
                ", peak=" + peak +
                '}';
    }
}
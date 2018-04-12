/**
  * Copyright 2018 bejson.com 
  */
package com.shuangmu.music.bean;
import java.util.List;

/**
 * Auto-generated: 2018-04-12 16:50:49
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class keyBean {

    private int code;
    private List<String> sip;
    private List<String> thirdip;
    private String testfile2g;
    private String testfilewifi;
    private String key;
    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

    public void setSip(List<String> sip) {
         this.sip = sip;
     }
     public List<String> getSip() {
         return sip;
     }

    public void setThirdip(List<String> thirdip) {
         this.thirdip = thirdip;
     }
     public List<String> getThirdip() {
         return thirdip;
     }

    public void setTestfile2g(String testfile2g) {
         this.testfile2g = testfile2g;
     }
     public String getTestfile2g() {
         return testfile2g;
     }

    public void setTestfilewifi(String testfilewifi) {
         this.testfilewifi = testfilewifi;
     }
     public String getTestfilewifi() {
         return testfilewifi;
     }

    public void setKey(String key) {
         this.key = key;
     }
     public String getKey() {
         return key;
     }

    @Override
    public String toString() {
        return "keyBean{" +
                "code=" + code +
                ", sip=" + sip +
                ", thirdip=" + thirdip +
                ", testfile2g='" + testfile2g + '\'' +
                ", testfilewifi='" + testfilewifi + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
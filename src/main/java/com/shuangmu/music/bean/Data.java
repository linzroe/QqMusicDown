/**
  * Copyright 2018 bejson.com 
  */
package com.shuangmu.music.bean;
import java.util.List;

public class Data {

    private long expiration;
    private List<Items> items;
    public void setExpiration(long expiration) {
         this.expiration = expiration;
     }
     public long getExpiration() {
         return expiration;
     }

    public void setItems(List<Items> items) {
         this.items = items;
     }
     public List<Items> getItems() {
         return items;
     }
	@Override
	public String toString() {
		return "Data [expiration=" + expiration + ", items=" + items + "]";
	}
     
}
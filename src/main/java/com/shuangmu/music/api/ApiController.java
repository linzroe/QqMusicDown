package com.shuangmu.music.api;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shuangmu.music.bean.Lists;
import com.shuangmu.music.bean.MusicBean;
import com.shuangmu.music.bean.TurlBean;
import com.shuangmu.music.bean.keyBean;
import okhttp3.*;
import okhttp3.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

/**
 * Created by Lin on 2018/4/11.
 */
@RestController()
@RequestMapping("/qqmusic")
public class ApiController {


    private String key="https://c.y.qq.com/base/fcgi-bin/fcg_musicexpress.fcg";//

    private String soso="https://c.y.qq.com/soso/fcgi-bin/client_search_cp?ct=24&qqmusic_ver=1298&new_json=1&remoteplace=txt.yqq.song&t=0&aggr=1&cr=1&catZhida=1&lossless=0&flag_qc=0&p=1&n=5&g_tk=5381&hostUin=0&format=jsonp&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq&needNewCode=0&w=小酒窝";
    public static void main(String[] args) {

        String guid="1010389200";
        System.out.println("guid："+guid);
        String key=GetKeys(guid);
        System.out.println(         JSONArray.toJSON(Getsoso(key,guid,"我继续"))   );

    }
    @RequestMapping("soso")
    public Object Soso(@RequestParam("name") String name){
        String guid="1010389200";

        System.out.println("查询："+name);
        String key=GetKeys(guid);
         return     JSONArray.toJSON( Getsoso(key,guid,name));

    }


    /**
     * 获取Kye
     * @return
     */
    public static String GetKeys(String guid){

        OkHttpClient client = new OkHttpClient();
        String key="";
        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        Request request = new Request.Builder()
                .url("https://c.y.qq.com/base/fcgi-bin/fcg_musicexpress.fcg?json=3&guid=1010389200&format=json")
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();
            String res=response.body().string();
            keyBean keys= JSONObject.parseObject(res,keyBean.class);
                key=keys.getKey();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return key;
    }

    public static  List< Map<String,Object>> Getsoso(String key,String guid,String Keywords){

        OkHttpClient client = new OkHttpClient();
        Random random = new Random(10);
         MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"format\"\r\n\r\njson\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"p\"\r\n\r\n1\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"n\"\r\n\r\n10\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"w\"\r\n\r\n我继续\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"aggr\"\r\n\r\n1\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"lossless\"\r\n\r\n1\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"cr\"\r\n\r\n1\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"new_json\"\r\n\r\n1\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
        Request request = new Request.Builder()
                .url("https://c.y.qq.com/soso/fcgi-bin/client_search_cp?format=json&p=1&n=20&w="+Keywords+"&aggr=1&lossless=1&cr=1&new_json=1")
                .get()
                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "dbc80543-703c-d0f8-13b5-a27f8e3c1324")
                .build();
        List< Map<String,Object>> Results =  new ArrayList<>();

        try {
            Response response = client.newCall(request).execute();
            String res=response.body().string().replaceAll("list","lists").replaceAll("switch","tswitch");

            res=res.replaceAll("callback\\(","").replaceAll("\\)","");

            System.out.println(res);

            MusicBean music= JSONArray.parseObject(res,MusicBean.class);

            List<Lists> lists =  music.getData().getSong().getLists();



            for (Lists musics: lists) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", musics.getTitle());
                map.put("album", musics.getAlbum().getTitle());
                map.put("singer", musics.getSinger().get(0).getTitle());

                String hd = "https://dl.stream.qqmusic.qq.com/M800" + musics.getFile().getMedia_mid() + ".mp3?guid="+guid+"&vkey=" + key + "&uid=0&fromtag=53";
                String MP3 = "https://dl.stream.qqmusic.qq.com/M500" + musics.getFile().getMedia_mid() + ".mp3?guid="+guid+"&vkey=" + key + "&uid=0&fromtag=53";
                String falc = "https://dl.stream.qqmusic.qq.com/F000" + musics.getFile().getMedia_mid() + ".flac?guid="+guid+"&vkey=" + key + "&uid=0&fromtag=53";
//                List<String> urlList = new ArrayList<>();
//                urlList.add(hd);
//                urlList.add(MP3);
//                urlList.add(falc);
//
//                urlList = GetUrl(urlList);
//                map.put("MP3", urlList.get(0));
//                map.put("HD", urlList.get(1));
//                map.put("FALC", urlList.get(2));
                map.put("MP3", MP3);
                map.put("HD", hd);
                map.put("FALC", falc);
                Results.add(map);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  Results;
    }

    public static List<String> GetUrl (List<String> urlList){
        OkHttpClient client = new OkHttpClient();
        List<String> newUrlList=new ArrayList<>();
        if(urlList.size()>0){

            for (int i = 0; i < urlList.size(); i++) {
                Request request = new Request.Builder()
                        .url("http://api.t.sina.com.cn/short_url/shorten.json?source=3271760578&url_long="+urlList.get(i))
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "8f5a2f51-0e1d-c6d0-27dc-6e1fe495386c")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    String str=response.body().string().replaceAll("\\[","").replaceAll("\\]","");
                    TurlBean urlbean=JSONObject.parseObject(str,TurlBean.class);
                    newUrlList.add(urlbean.getUrl_short());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        return  newUrlList;
    }

}

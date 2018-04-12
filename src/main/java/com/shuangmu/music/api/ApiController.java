package com.shuangmu.music.api;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shuangmu.music.bean.MusicBean;
import com.shuangmu.music.bean.keyBean;
import okhttp3.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Lin on 2018/4/11.
 */
@RestController()
@RequestMapping("/qqmusic")
public class ApiController {


    private String key="https://c.y.qq.com/base/fcgi-bin/fcg_musicexpress.fcg";//随机十位数 3233223232

    private String soso="https://c.y.qq.com/soso/fcgi-bin/client_search_cp?ct=24&qqmusic_ver=1298&new_json=1&remoteplace=txt.yqq.song&t=0&aggr=1&cr=1&catZhida=1&lossless=0&flag_qc=0&p=1&n=5&g_tk=5381&hostUin=0&format=jsonp&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq&needNewCode=0&w=小酒窝";

    public static void main(String[] args) {
        Random random = new Random(10);
        Long guid=random.nextLong();
        String key=GetKeys(guid);

         Getsoso(key,guid);

    }

    /**
     * 获取Kye
     * @return
     */
    public static String GetKeys(Long guid){

        OkHttpClient client = new OkHttpClient();
        String key="";
        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        Request request = new Request.Builder()
                .url("https://c.y.qq.com/base/fcgi-bin/fcg_musicexpress.fcg?json=3&guid=" +guid+"&format=json")
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

    public static void Getsoso(String key,Long guid){

        OkHttpClient client = new OkHttpClient();
        Random random = new Random(10);
         MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"format\"\r\n\r\njson\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"p\"\r\n\r\n1\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"n\"\r\n\r\n10\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"w\"\r\n\r\n我继续\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"aggr\"\r\n\r\n1\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"lossless\"\r\n\r\n1\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"cr\"\r\n\r\n1\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"new_json\"\r\n\r\n1\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
        Request request = new Request.Builder()
                .url("https://c.y.qq.com/soso/fcgi-bin/client_search_cp?ct=24&qqmusic_ver=1298&new_json=1&remoteplace=txt.yqq.song&t=0&aggr=1&cr=1&catZhida=1&lossless=0&flag_qc=0&p=1&n=1&g_tk=5381&hostUin=0&format=jsonp&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq&needNewCode=0&w=圣所")
                .get()
                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "dbc80543-703c-d0f8-13b5-a27f8e3c1324")
                .build();

        try {
            Response response = client.newCall(request).execute();
            String res=response.body().string().replaceAll("list","lists").replaceAll("switch","tswitch");
            res=res.replaceAll("callback\\(","").replaceAll("\\)","");


//            System.out.println(res);
            MusicBean music= JSONArray.parseObject(res,MusicBean.class);

             String mid=music.getData().getSong().getLists().get(0).getFile().getMedia_mid();
            String m500="https://dl.stream.qqmusic.qq.com/M500"+mid+".mp3?vkey="+key+"&guid="+guid+"&uid=0&fromtag=30";
            String m5800="https://dl.stream.qqmusic.qq.com/M800"+mid+".mp3?vkey="+key+"&guid="+guid+"&uid=0&fromtag=30";
            String ape="https://dl.stream.qqmusic.qq.com/A000"+mid+".ape?vkey="+key+"&guid="+guid+"&uid=0&fromtag=30";
            String falc="https://dl.stream.qqmusic.qq.com/F000"+mid+".flac?vkey="+key+"&guid="+guid+"&uid=0&fromtag=30";
//https://dl.stream.qqmusic.qq.com/M800100bDLDa0zODQF.mp3?vkey=7597D13CEF9380E93DECBDE339EF4AE21DEC00D5332F6B17364055B6F875C5B615D279C630DAF27300917DD14AE382CCE83DE65BE43C58BE&guid=1234567890&uin=1008611&fromtag=64
//https://dl.stream.qqmusic.qq.com/M800100bDLDa0zODQF.mp3?vkey=60F79ED98476BEF922D39504DACBD8D5CB0CF12B25BA69B9C69630E7CE396A9BEBD01EF06DA3A801747FCBF9B5510402BEE897B840654CFD&guid=191577516&uid=0&fromtag=30
            System.out.println(m500);
            System.out.println(m5800);
            System.out.println(ape);
            System.out.println(falc);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

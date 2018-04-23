package com.shuangmu.music.api;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shuangmu.music.bean.Lists;
import com.shuangmu.music.bean.MusicBean;
import com.shuangmu.music.bean.TurlBean;
import com.shuangmu.music.bean.keyBean;
import com.shuangmu.music.bean.lryBean;

import okhttp3.*;
import okhttp3.RequestBody;
import sun.misc.BASE64Decoder;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * Created by Lin on 2018/4/11.
 */
@RestController()
@RequestMapping("/qqmusic")
public class ApiController {

    private  Logger logger = Logger.getLogger(getClass());

    private String key="https://c.y.qq.com/base/fcgi-bin/fcg_musicexpress.fcg";//
	final static BASE64Decoder decoder = new BASE64Decoder();
    private String soso="https://c.y.qq.com/soso/fcgi-bin/client_search_cp?ct=24&qqmusic_ver=1298&new_json=1&remoteplace=txt.yqq.song&t=0&aggr=1&cr=1&catZhida=1&lossless=0&flag_qc=0&p=1&n=5&g_tk=5381&hostUin=0&format=jsonp&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq&needNewCode=0&w=小酒窝";
    public static void main(String[] args) throws Exception {

//        String guid="1010389200";
//        System.out.println("guid："+guid);
//        String key=GetKeys(guid);
//        String name="小酒窝";
//        System.out.println(JSONArray.toJSON(Getsoso(key,guid,name))   );
//    	final BASE64Decoder decoder = new BASE64Decoder();
//    	String a="W3RpOuWwj+mFkueqnV0KW2FyOue+pOaYn10KW2FsOumdk+WjsOivlemfsyBDRDFdCltieTpdCltvZmZzZXQ6MF0KWzAwOjAwLjEwXeWwj+mFkueqnSAtIOe+pOaYnwpbMDA6MDAuMjBdClswMDoyMS44MF3miJHov5jlnKjlr7vmib4KWzAwOjI0LjI5XQpbMDA6MjQuOTJd5LiA5Liq5L6d6Z2g5ZKM5LiA5Liq5oul5oqxClswMDozMC45MF0KWzAwOjMxLjU5Xeiwgeabv+aIkeeliOeltyDmm7/miJHng6bmgbwKWzAwOjM3LjA4XQpbMDA6MzcuODld5Li65oiR55Sf5rCU5Li65oiR6Ze5ClswMDo0MC4xMl0KWzAwOjQxLjk0XeW5uOemj+W8gOWni+aciemihOWFhgpbMDA6NDQuNjJdClswMDo0Ni4wNl3nvJjliIborqnmiJHku6zmhaLmhaLntKfpnaAKWzAwOjQ5LjI0XQpbMDA6NTEuMDRd54S25ZCO5a2k5Y2V6KKr5ZCe5rKh5LqGClswMDo1NC4yMl3ml6DogYrlj5jlvpfmnInor53ogYog5pyJ5Y+Y5YyW5LqGClswMDo1OS43Ml0KWzAxOjAwLjg1XeWwj+mFkueqnemVv+edq+avmwpbMDE6MDMuODRd5piv5L2g5pyA576O55qE6K6w5Y+3ClswMTowNi4wOV3miJHmr4/lpKnnnaHkuI3nnYAg5oOz5b+15L2g55qE5b6u56yRClswMToxMC4wMF0KWzAxOjExLjk5XeS9oOS4jeefpemBkyDkvaDlr7nmiJHlpJrkuYjph43opoEKWzAxOjE2LjExXQpbMDE6MTcuMDRd5pyJ5LqG5L2g55Sf5ZG95a6M5pW055qE5Yia5aW9ClswMToxOS44NV0KWzAxOjIxLjIzXeWwj+mFkueqnemVv+edq+avmwpbMDE6MjMuOTdd6L+35Lq65b6X5peg5Y+v5pWR6I2vClswMToyNi4zNF3miJHmlL7mhaLkuobmraXosIMKWzAxOjI5LjA5XeaEn+inieWDj+aYr+WWnemGieS6hgpbMDE6MzAuMDBdClswMTozMi4xMF3nu4jkuo7mib7liLAg5b+D5pyJ54G154qA55qE576O5aW9ClswMTozNi4yN10KWzAxOjM3LjIxXeS4gOi+iOWtkOaaluaalueahOWlvQpbMDE6NDAuMTVdClswMTo0Mi4yN13miJHmsLjov5zniLHkvaDliLDogIEKWzAxOjQ1LjIwXQpbMDI6MDcuNjFd5bm456aP5byA5aeL5pyJ6aKE5YWGClswMjoxMC41NF0KWzAyOjExLjk4Xee8mOWIhuiuqeaIkeS7rOaFouaFoue0p+mdoApbMDI6MTUuMTddClswMjoxNy4wM13nhLblkI7lraTljZXooqvlkJ7msqHkuoYKWzAyOjIwLjI4XeaXoOiBiuWPmOW+l+acieivneiBiiDmnInlj5jljJbkuoYKWzAyOjI1LjU4XQpbMDI6MjYuMzld5ZCIClswMjoyNi44NF3lsI/phZLnqp3plb/nnavmr5sKWzAyOjI5LjY0XeaYr+S9oOacgOe+jueahOiusOWPtwpbMDI6MzEuMDBdClswMjozMi4yMl3miJHmr4/lpKnnnaHkuI3nnYAg5oOz5b+15L2g55qE5b6u56yRClswMjozNi45MF0KWzAyOjM3LjkwXeS9oOS4jeefpemBkyDkvaDlr7nmiJHlpJrkuYjph43opoEKWzAyOjQxLjk2XQpbMDI6NDIuNjRd5pyJ5LqG5L2g55Sf5ZG95a6M5pW055qE5Yia5aW9ClswMjo0NS43Nl0KWzAyOjQ3LjIwXeWwj+mFkueqnemVv+edq+avmwpbMDI6NDkuODhd6L+35Lq65b6X5peg5Y+v5pWR6I2vClswMjo1Mi4yNV3miJHmlL7mhaLkuobmraXosIMKWzAyOjU1LjAwXeaEn+inieWDj+aYr+WWnemGieS6hgpbMDI6NTcuMDddClswMjo1Ny45M13nu4jkuo7mib7liLAg5b+D5pyJ54G154qA55qE576O5aW9ClswMzowMS45OV0KWzAzOjAyLjk4XeS4gOi+iOWtkOaaluaalueahOWlvSDmiJHmsLjov5zniLHkvaDliLDogIEKWzAzOjExLjAzXQpbMDM6MTQuOTBd5bCP6YWS56qd6ZW/552r5q+bClswMzoxNy42NV3ov7fkurrlvpfml6Dlj6/mlZHoja8KWzAzOjIwLjA5XeaIkeaUvuaFouS6huatpeiwgwpbMDM6MjIuNjVd5oSf6KeJ5YOP5piv5Zad6YaJ5LqGClswMzoyNC45NV0KWzAzOjI1LjgzXee7iOS6juaJvuWIsCDlv4PmnInngbXnioDnmoTnvo7lpb0KWzAzOjMwLjAwXQpbMDM6MzAuODFd5LiA6L6I5a2Q5pqW5pqW55qE5aW9ClswMzozMy44MV0KWzAzOjM1LjgxXeaIkeawuOi/nOeIseS9oOWIsOiAgQ==";
//    	System.out.println(new String(decoder.decodeBuffer(a), "UTF-8"));

    	System.out.println(lyric("003adisX1z5bD9"));
    	
//    	try {
//			lyric("003ojkx92JQyP0");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
    	
    }



    @GetMapping("lyric/{id}.lrc")
    public static Object lyric(@PathVariable("id") String id) throws IOException{
    	
    	OkHttpClient client = new OkHttpClient();

    	Request request = new Request.Builder()
    	  .url("https://c.y.qq.com/lyric/fcgi-bin/fcg_query_lyric_new.fcg?songmid="+id+"&g_tk=5381")
    	  .get()
    	  .addHeader("accept-language", "zh-CN,zh;q=0.9")
    	  .addHeader("referer", "https://y.qq.com/portal/player.html")
    	  .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
    	  .addHeader("cache-control", "no-cache")
    	  .addHeader("postman-token", "e26ff066-24aa-765d-95a2-fad97c2dfeb0")
    	  .build();

    	Response response = client.newCall(request).execute();
    	String json=response.body().string().substring(18);
    	String data=json.substring(0,json.length()-1);
    	lryBean lrys=JSONArray.parseObject(data,lryBean.class);
    	String jsonlrc=new String(decoder.decodeBuffer(lrys.getLyric()), "UTF-8");
    	return jsonlrc;
    }

    @PostMapping("soso")
    public Object Soso(@RequestParam("name") String name,HttpServletRequest request,HttpServletResponse response){
        String guid="1010389200";
        response.addHeader("Access-Control-Allow-Origin", "*");

        String ip=   getLocalIp(request);
         if(name==null||name==""){
             return  null;
         }

         String origin=request.getHeader("origin").toString();
        Map<String, Object> banmap = new HashMap<String, Object>();
        List< Map<String,Object>> banlist =  new ArrayList<>();

        banmap.put("name", "贵站未保留任何版权信息或作者信息,对贵站停用接口解析服务");
        banmap.put("album", "Ban");
        banmap.put("singer", "admin@09L.me");
        banmap.put("MP3", "https://y.09l.me");
        banmap.put("FALC", "https://y.09l.me");
        banmap.put("HD", "https://y.09l.me");
         banlist.add(banmap);

        logger.info("查询："+name+"   ip:"+ip+ "   origin:"+origin);

         switch (origin){
            case "http://4ppt.cn":
                 return JSONArray.toJSON(banlist);
            case "https://4ppt.cn":
                 return JSONArray.toJSON(banlist);
            case "http://liumuze.com":
                returntp://4ppt.cn":
                 return JSONArray.toJSON(banlist);
            case "https://liumuze.com":
                return JSONArray.toJSON(banlist);
        }


        String key=GetKeys(guid);
         return     JSONArray.toJSON( Getsoso(key,guid,name.trim()));

    }

    public  String getLocalIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip =  forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if(forwarded != null){
                    forwarded = forwarded.split(",")[0];
                }
                ip =  forwarded;
            }
        }
        return ip;
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
       //     System.out.println(res);
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
                String ape = "https://dl.stream.qqmusic.qq.com/A000" + musics.getFile().getMedia_mid() + ".ape?guid="+guid+"&vkey=" + key + "&uid=0&fromtag=53";
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

                map.put("img", "https://y.gtimg.cn/music/photo_new/T002R300x300M000"+musics.getAlbum().getMid()+".jpg?max_age=2592000");
                map.put("lyr", musics.getMid());
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

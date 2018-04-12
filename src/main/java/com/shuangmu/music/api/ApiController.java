package com.shuangmu.music.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shuangmu.music.bean.KeysBean;
import com.shuangmu.music.utils.ApiURL;
import net.dongliu.requests.Client;
import net.dongliu.requests.HeadOnlyRequestBuilder;
import net.dongliu.requests.Response;
import net.dongliu.requests.Session;
import net.dongliu.requests.exception.RequestException;
import net.dongliu.requests.struct.Cookie;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Lin on 2018/4/11.
 */
@RestController()
@RequestMapping("/qqmusic")
public class ApiController {

    // 发生ngnix 404 时的重试次数
    private static int retryTimesOnFailed = 3;
    // 客户端id，固定的
    private static final long Client_ID = 53999199;
    // 消息发送失败重发次数
    private static final long RETRY_TIMES = 5;
    // 客户端
    private Client client;
    // 会话
    private Session session;
    // 二维码令牌
    private String qrsig;
    // 鉴权参数
    private String ptwebqq;
    private String vfwebqq;
    private long uin;
    private String psessionid;
    private String keysvalus="";



    @RequestMapping("/qrcode")
    public String GetQrCode(){
        this.client = Client.pooled().maxPerRoute(5).maxTotal(10).build();
        this.session = client.session();
        String qrcode = login().replaceAll("\n", "").replaceAll("\r", "");
        System.out.println("获取二维码");
        return qrcode;
    }

    private String login() {
        String qrcode = getQRCode();
        return qrcode;
    }

    @RequestMapping("login")
    public Integer  getlogin() {
        Integer type=0;
        Response<String> response = get(ApiURL.VERIFY_QR_CODE, hash33(qrsig));
        String result = response.getBody();
        String url="";

        if (result.contains("二维码未失效")) {
            type=0;
        } else if (result.contains("二维码认证中")) {
            type=1;
        }else if (result.contains("登录成功")) {
            type=2;
            for (String content : result.split("','")) {
                if (content.startsWith("http")) {
                    url= content;
                }
            }
            getPtwebqq(url);
            getVfwebqq();
            KeysBean keys=getUinAndPsessionid();
            keysvalus=keys.getData().getItems().get(0).getVkey();
        }else {
            getQRCode();
            type=3;
        }

        return type;
    }


    @RequestMapping("/getmusic/{key}")
    public  Map<String, Object> getmusic(@PathVariable("key") String key){
        //https://y.qq.com/n/yqq/song/0005jrRs05ELQv.html
        String name="https://y.qq.com/n/yqq/song/"+key.trim()+".html";
        System.out.println("name:"+name);
        String media_mid="";
        String srtResult="";
        srtResult=Get(name);
        Integer g_SongData=srtResult.indexOf("strMediaMid\":\"")+14;
        Integer endg_SongData=srtResult.indexOf("sizeape")-3;
        media_mid=srtResult.substring(g_SongData,endg_SongData);
        System.out.println(media_mid);



        Map<String, Object> map= null;

        map.put("mp3", url("M500"+media_mid+".mp3?vkey="+keysvalus));
        map.put("hd", url("M800"+media_mid+".mp3?vkey="+keysvalus));
        map.put("ape", url("A000"+media_mid+".ape?vkey="+keysvalus));
        map.put("flac", url("F000"+media_mid+".flac?vkey="+keysvalus));
        System.out.println(map.toString());
        return map;
    }

    public String url(String key) {

        String url="http://dl.stream.qqmusic.qq.com/"+key+"&guid=1234567890&uin=1008611&fromtag=64".trim();
        return url;
    }


    public String getkey() {


        String key="";
        return key;
    }


    public ApiController() {
        this.client = Client.pooled().maxPerRoute(5).maxTotal(10).build();
        this.session = client.session();

    }

    // 登录流程1：获取二维码
    private String getQRCode() {
         String qrcode = "";
        // 本地存储二维码图片
        String filePath;
        try {
            String uuid = UUID.randomUUID().toString();
            filePath = new File(uuid + ".png").getCanonicalPath();
        } catch (IOException e) {
            throw new IllegalStateException("二维码保存失败");
        }
        Response response = session.get(ApiURL.GET_QR_CODE.getUrl()).addHeader("User-Agent", ApiURL.USER_AGENT)
                .file(filePath);
        for (Cookie cookie : response.getCookies()) {
            if (Objects.equals(cookie.getName(), "qrsig")) {
                qrsig = cookie.getValue();
                break;
            }
        }
        try {
            qrcode = "data:image/png;base64," + encodeBase64File(filePath);
            deleteFile(filePath);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return qrcode;
    }





    // 登录流程3：获取ptwebqq
    private void getPtwebqq(String url) {
        Response<String> response = get(ApiURL.GET_PTWEBQQ, url);
        this.ptwebqq = response.getCookies().get("ptwebqq").iterator().next().getValue();
    }

    // 登录流程4：获取vfwebqq
    private void getVfwebqq() {
        Response<String> response = get(ApiURL.GET_VFWEBQQ, ptwebqq);
        int retryTimes4Vfwebqq = retryTimesOnFailed;
        while (response.getStatusCode() == 404 && retryTimes4Vfwebqq > 0) {
            response = get(ApiURL.GET_VFWEBQQ, ptwebqq);
            retryTimes4Vfwebqq--;
        }
        this.vfwebqq = getJsonObjectResult(response).getString("vfwebqq");

    }

    // 登录流程5：获取uin和psessionid
    private KeysBean  getUinAndPsessionid() {
        JSONObject r = new JSONObject();
        r.put("ptwebqq", ptwebqq);
        r.put("clientid", Client_ID);
        r.put("psessionid", "");
        r.put("status", "online");

        Response<String> response = post(ApiURL.GET_UIN_AND_PSESSIONID, r);
        JSONObject result = getJsonObjectResult(response);

        this.psessionid = result.getString("psessionid");
        this.uin = result.getLongValue("uin");

        Response<String> responsess = get(ApiURL.key, r);
        KeysBean keys= JSONArray.parseObject(responsess.getBody().toString(), KeysBean.class);
        return keys;
    }

    // 发送get请求
    private Response<String> get(ApiURL url, Object... params) {
        HeadOnlyRequestBuilder request = session.get(url.buildUrl(params)).addHeader("User-Agent", ApiURL.USER_AGENT);
        if (url.getReferer() != null) {
            request.addHeader("Referer", url.getReferer());
        }
        return request.text(StandardCharsets.UTF_8);
    }

    // 发送post请求
    private Response<String> post(ApiURL url, JSONObject r) {
        return session.post(url.getUrl()).addHeader("User-Agent", ApiURL.USER_AGENT)
                .addHeader("Referer", url.getReferer()).addHeader("Origin", url.getOrigin())
                .addForm("r", r.toJSONString()).text(StandardCharsets.UTF_8);
    }

    // 发送post请求，失败时重试
    private Response<String> postWithRetry(ApiURL url, JSONObject r) {
        int times = 0;
        Response<String> response;
        do {
            response = post(url, r);
            times++;
        } while (times < RETRY_TIMES && response.getStatusCode() != 200);
        return response;
    }

    // 获取返回json的result字段（JSONObject类型）
    private static JSONObject getJsonObjectResult(Response<String> response) {
        return getResponseJson(response).getJSONObject("result");
    }

    // 获取返回json的result字段（JSONArray类型）
    private static JSONArray getJsonArrayResult(Response<String> response) {
        return getResponseJson(response).getJSONArray("result");
    }

    // 检查消息是否发送成功
    private static void checkSendMsgResult(Response<String> response) {
        if (response.getStatusCode() != 200) {

            System.out.print(String.format("发送失败，Http返回码[%d]", response.getStatusCode()));
        }
        JSONObject json = JSON.parseObject(response.getBody());
        Integer errCode = json.getInteger("retcode");
        if (errCode != null && errCode == 0) {
           System.out.print( "发送成功");
        } else {
            System.out.print(String.format("发送失败，Api返回码[%d]", json.getInteger("retcode")));
        }
    }

    // 检验Json返回结果
    private static JSONObject getResponseJson(Response<String> response) {
        if (response.getStatusCode() != 200) {
            throw new RequestException(String.format("请求失败，Http返回码[%d]", response.getStatusCode()));
        }
        JSONObject json = JSON.parseObject(response.getBody());
        Integer retCode = json.getInteger("retcode");
        if (retCode == null) {
            throw new RequestException(String.format("请求失败，Api返回异常", retCode));
        } else if (retCode != 0) {
            switch (retCode) {
                case 103: {
                    System.out.print("请求失败，Api返回码[103]。你需要进入http://w.qq.com，检查是否能正常接收消息。如果可以的话点击[设置]->[退出登录]后查看是否恢复正常");
                    break;
                }
                case 100100: {
                    System.out.print("请求失败，Api返回码[100100]");
                    break;
                }
                default: {
                    throw new RequestException(String.format("请求失败，Api返回码[%d]", retCode));
                }
            }
        }
        return json;
    }

    // hash加密方法
    private String hash() {
        return hash(uin, ptwebqq);
    }

    // 线程暂停
    private static void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            // 忽略InterruptedException
        }
    }

    // hash加密方法
    private static String hash(long x, String K) {
        int[] N = new int[4];
        for (int T = 0; T < K.length(); T++) {
            N[T % 4] ^= K.charAt(T);
        }
        String[] U = { "EC", "OK" };
        long[] V = new long[4];
        V[0] = x >> 24 & 255 ^ U[0].charAt(0);
        V[1] = x >> 16 & 255 ^ U[0].charAt(1);
        V[2] = x >> 8 & 255 ^ U[1].charAt(0);
        V[3] = x & 255 ^ U[1].charAt(1);

        long[] U1 = new long[8];

        for (int T = 0; T < 8; T++) {
            U1[T] = T % 2 == 0 ? N[T >> 1] : V[T >> 1];
        }

        String[] N1 = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
        String V1 = "";
        for (long aU1 : U1) {
            V1 += N1[(int) ((aU1 >> 4) & 15)];
            V1 += N1[(int) (aU1 & 15)];
        }
        return V1;
    }


    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 将文件转成base64 字符串
     *
     * @return
     * @throws Exception
     */

    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);

    }

    // 用于生成ptqrtoken的哈希函数
    private static int hash33(String s) {
        int e = 0, n = s.length();
        for (int i = 0; n > i; ++i)
            e += (e << 5) + s.charAt(i);
        return 2147483647 & e;
    }

    public static String Get(String url){
        String json="";


        CloseableHttpClient httpCilent2 = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000) // 设置连接超时时间
                .setConnectionRequestTimeout(5000) // 设置请求超时时间
                .setSocketTimeout(5000).setRedirectsEnabled(true)// 默认允许自动重定向
                .build();


        HttpGet httpGet2 = new HttpGet(url);
        httpGet2.setHeader("cache-control", "no-cache");
        httpGet2.setHeader("postman-token", UUID.randomUUID().toString());
        httpGet2.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");

        httpGet2.setConfig(requestConfig);
        try {
            HttpResponse httpResponse = httpCilent2.execute(httpGet2);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                json = EntityUtils.toString(httpResponse.getEntity());// 获得返回的结果
            } else if (httpResponse.getStatusLine().getStatusCode() == 400) {
                // ..........
            } else if (httpResponse.getStatusLine().getStatusCode() == 500) {
                // .............
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpCilent2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return json;
    }

    public void close() throws IOException {

    }

}

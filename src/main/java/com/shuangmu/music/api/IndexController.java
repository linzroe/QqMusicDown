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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
@Controller
public class IndexController {

    @RequestMapping(value={"/","index.html"})
    public String index(ModelMap map) {
        map.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }

}

package com.shuangmu.music.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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

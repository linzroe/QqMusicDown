package com.shuangmu.music.utils;

/**
 * Api的请求地址和Referer
 *
 * @author ScienJus
 * @date 2015/12/19
 */
public enum ApiURL {

    key(
            "http://c.y.qq.com/base/fcgi-bin/fcg_music_express_mobile3.fcg?g_tk=0&loginUin=1008611&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq&needNewCode=0&cid=205361747&uin=1008611&songmid=003a1tne1nSz1Y&filename=C400003a1tne1nSz1Y.m4a&guid=1234567890",
            ""
    ),
    GET_QR_CODE(
    		"https://ssl.ptlogin2.qq.com/ptqrshow?appid=501004106&e=0&l=M&s=5&d=72&v=4&t=0.1",
    		""
    		),
    VERIFY_QR_CODE(
            "https://ssl.ptlogin2.qq.com/ptqrlogin?" +
                    "ptqrtoken={1}&webqq_type=10&remember_uin=1&login2qq=1&aid=501004106&" +
                    "u1=https%3A%2F%2Fw.qq.com%2Fproxy.html%3Flogin2qq%3D1%26webqq_type%3D10&" +
                    "ptredirect=0&ptlang=2052&daid=164&from_ui=1&pttype=1&dumy=&fp=loginerroralert&0-0-157510&" +
                    "mibao_css=m_webqq&t=undefined&g=1&js_type=0&js_ver=10184&login_sig=&pt_randsalt=3",
            "https://ui.ptlogin2.qq.com/cgi-bin/login?" +
                    "daid=164&target=self&style=16&mibao_css=m_webqq&appid=501004106&enable_qlogin=0&no_verifyimg=1&" +
                    "s_url=https%3A%2F%2Fw.qq.com%2Fproxy.html&f_url=loginerroralert&strong_login=1&login_state=10&t=20131024001"
    ),
    GET_PTWEBQQ(
            "{1}",
            null
    ),
    GET_VFWEBQQ(
            "https://s.web2.qq.com/api/getvfwebqq?ptwebqq={1}&clientid=53999199&psessionid=&t=0.1",
            "https://s.web2.qq.com/proxy.html?v=20130916001&callback=1&id=1"
    ),
    GET_UIN_AND_PSESSIONID(
            "https://d1.web2.qq.com/channel/login2",
            "https://d1.web2.qq.com/proxy.html?v=20151105001&callback=1&id=2"
    ),
    GET_GROUP_LIST(
            "https://s.web2.qq.com/api/get_group_name_list_mask2",
            "https://d1.web2.qq.com/proxy.html?v=20151105001&callback=1&id=2"
    ),
    POLL_MESSAGE(
            "https://d1.web2.qq.com/channel/poll2",
            "https://d1.web2.qq.com/proxy.html?v=20151105001&callback=1&id=2"
    ),
    SEND_MESSAGE_TO_GROUP(
            "https://d1.web2.qq.com/channel/send_qun_msg2",
            "https://d1.web2.qq.com/proxy.html?v=20151105001&callback=1&id=2"
    ),
    GET_FRIEND_LIST(
            "https://s.web2.qq.com/api/get_user_friends2",
            "https://s.web2.qq.com/proxy.html?v=20130916001&callback=1&id=1"
    ),
    SEND_MESSAGE_TO_FRIEND(
            "https://d1.web2.qq.com/channel/send_buddy_msg2",
            "https://d1.web2.qq.com/proxy.html?v=20151105001&callback=1&id=2"
    ),
    GET_DISCUSS_LIST(
            "https://s.web2.qq.com/api/get_discus_list?clientid=53999199&psessionid={1}&vfwebqq={2}&t=0.1",
            "https://s.web2.qq.com/proxy.html?v=20130916001&callback=1&id=1"
    ),
    SEND_MESSAGE_TO_DISCUSS(
            "https://d1.web2.qq.com/channel/send_discu_msg2",
            "https://d1.web2.qq.com/proxy.html?v=20151105001&callback=1&id=2"
    ),
    GET_ACCOUNT_INFO(
            "https://s.web2.qq.com/api/get_self_info2?t=0.1",
            "https://s.web2.qq.com/proxy.html?v=20130916001&callback=1&id=1"
    ),
    GET_RECENT_LIST(
            "https://d1.web2.qq.com/channel/get_recent_list2",
            "https://d1.web2.qq.com/proxy.html?v=20151105001&callback=1&id=2"
    ),
    GET_FRIEND_STATUS(
            "https://d1.web2.qq.com/channel/get_online_buddies2?vfwebqq={1}&clientid=53999199&psessionid={2}&t=0.1",
            "https://d1.web2.qq.com/proxy.html?v=20151105001&callback=1&id=2"
    ),
    GET_GROUP_INFO(
            "https://s.web2.qq.com/api/get_group_info_ext2?gcode={1}&vfwebqq={2}&t=0.1",
            "https://s.web2.qq.com/proxy.html?v=20130916001&callback=1&id=1"
    ),
    GET_QQ_BY_ID(
            "https://s.web2.qq.com/api/get_friend_uin2?tuin={1}&type=1&vfwebqq={2}&t=0.1",
            "https://s.web2.qq.com/proxy.html?v=20130916001&callback=1&id=1"
    ),
    GET_DISCUSS_INFO(
            "https://d1.web2.qq.com/channel/get_discu_info?did={1}&vfwebqq={2}&clientid=53999199&psessionid={3}&t=0.1",
            "https://d1.web2.qq.com/proxy.html?v=20151105001&callback=1&id=2"
    ),
    GET_FRIEND_INFO(
            "https://s.web2.qq.com/api/get_friend_info2?tuin={1}&vfwebqq={2}&clientid=53999199&psessionid={3}&t=0.1",
            "https://s.web2.qq.com/proxy.html?v=20130916001&callback=1&id=1"
    );

    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";

    private String url;

    private String referer;

    ApiURL(String url, String referer) {
        this.url = url;
        this.referer = referer;
    }

    public String getUrl() {
        return url;
    }


    public String getReferer() {
        return referer;
    }

    public String buildUrl(Object... params) {
        int i = 1;
        String url = this.url;
        for (Object param : params) {
            url = url.replace("{" + i++ + "}", param.toString());
        }
        return url;
    }

    public String getOrigin() {
        return this.url.substring(0, url.lastIndexOf("/"));
    }
}
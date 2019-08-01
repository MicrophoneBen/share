package com.suntek.log4j2.util;

import org.springframework.web.client.RestTemplate;

/**
 * @author :wuyuhao
 * @version： 2019年07月18日
 * @since： 2019年07月18日
 */
public class HttpUtil {
    private static RestTemplate restTemplate = new RestTemplate();
    public static String post(String url,String body){
        return restTemplate.postForEntity(url,body,String.class).getBody();
    }
}

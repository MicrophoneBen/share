package com.suntek.apollo.config.controller;

import com.suntek.apollo.config.redis.SampleRedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangbingquan
 * @version 2019年07月31日
 * @since 2019年07月31日
 **/
@RestController
public class RedisConfigController {
    @Autowired
    SampleRedisConfig sampleRedisConfig;

    @GetMapping("/redis")
    public Object getRedisConfig(){
        return sampleRedisConfig;
    }
}

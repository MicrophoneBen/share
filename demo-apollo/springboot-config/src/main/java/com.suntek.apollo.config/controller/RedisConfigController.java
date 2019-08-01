package com.suntek.apollo.config.controller;

import com.suntek.apollo.config.dto.ConfigDto;
import com.suntek.apollo.config.redis.SampleRedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangbingquan
 * @version 2019年07月31日
 * @since 2019年07月31日
 **/
@RestController
public class RedisConfigController {
    private final SampleRedisConfig sampleRedisConfig;

    private final ConfigDto configDto;

    @Autowired
    public RedisConfigController(@Qualifier(value = "ApolloConfig") ConfigDto configDto, SampleRedisConfig sampleRedisConfig) {
        this.configDto = configDto;
        this.sampleRedisConfig = sampleRedisConfig;
    }

    @GetMapping("/redis")
    public Object getRedisConfig(){
        Map<String,Object> map = new HashMap<>(16);
        map.put("List", sampleRedisConfig.toString());
        return map;
    }

    @GetMapping("/config")
    public String getApolloConfig(){
        return configDto.toString();
    }
}

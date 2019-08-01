package com.suntek.apollo.config.dto;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhangbingquan
 * @version 2019年08月01日
 * @since 2019年08月01日
 **/
@Data
@Component("ApolloConfig")
@RefreshScope
public class ConfigDto {
    private static final Logger logger = LoggerFactory.getLogger(ConfigDto.class);
    /**
     * 数据库连接信息
     */
    @Value("${spring.datasource.url:#{null}}")
    private String url;

    /**
     * zookeeper连接信息
     */
    @Value("${zookeeper.connect:#{null}}")
    private String zkConnect;

    /**
     * kafka连接信息
     */
    @Value("${kafka.connect:#{null}}")
    private String kafkaConnect;

    /**
     * cas连接信息
     */
    @Value("${cas.server:#{null}}")
    private String casServer;

    /**
     * redis连接信息
     */
    @Value("${redis.server:#{null}}")
    private String redisConnect;


    @PostConstruct
    private void initialize() {
        logger.debug(
                "ApolloConfig initialized - dataSourceUrl: {}, zkConnect: {}, kafkaConnect: {}, casServer: {}, redisConnect: {}",
                url, zkConnect, kafkaConnect, casServer, redisConnect);
    }
}

package com.suntek.apollo.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhangbingquan
 * @version 2019年07月31日
 * @since 2019年07月31日
 **/

@Slf4j
@EnableSwagger2
@EnableApolloConfig
@SpringBootApplication
public class ApolloApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApolloApplication.class, args);
    }
}

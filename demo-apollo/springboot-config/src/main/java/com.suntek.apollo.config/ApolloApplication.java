package com.suntek.apollo.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author zhangbingquan
 * @version 2019年07月31日
 * @since 2019年07月31日
 **/

@Slf4j
@EnableSwagger2
@EnableApolloConfig(value = {"application.yml","application"})
@SpringBootApplication
public class ApolloApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApolloApplication.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        try {
            String path = System.getProperty("catalina.home");
            path = path + "/conf/apollo-config.yml";
            YamlPropertiesFactoryBean yml = new YamlPropertiesFactoryBean();
            InputStream in = new FileInputStream(new File(path));
            Resource resource = new InputStreamResource(in);
            yml.setResources(resource);
            application.properties(Objects.requireNonNull(yml.getObject()));
        } catch (Exception e) {
            log.warn("读取外置配置文件异常{}，将使用默认application.yml作为配置文件", e);
        }
        return application.sources(ApolloApplication.class);
    }

}

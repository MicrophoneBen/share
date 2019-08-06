package com.ctrip.framework.apollo.use.cases.dynamic.datasource;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.ctrip.framework.apollo.use.cases.dynamic.datasource.mapper.UserMapper;
import com.ctrip.framework.apollo.use.cases.dynamic.datasource.repository.UserRepository;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by wuyuhao on 2018/6/25. Content :动态数据源实例
 */
@EnableApolloConfig
@SpringBootApplication
@MapperScan("com.ctrip.framework.apollo.use.cases.dynamic.datasource.mapper")
public class Application implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Autowired
  private UserMapper userMapper;

  @Override
  public void run(String... args) throws Exception {
    Executors.newSingleThreadExecutor().submit(() -> {
      while (true) {
        try {
          System.err.println(userMapper.selectByPrimaryKey(1).getName());
          TimeUnit.SECONDS.sleep(1);
        } catch (Throwable ex) {
          ex.printStackTrace();
        }
      }
    });
  }
}

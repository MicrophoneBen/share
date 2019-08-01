package com.suntek.apollo.config.redis;

import com.ctrip.framework.apollo.core.ConfigConsts;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.suntek.apollo.config.dto.ConfigDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author zhangbingquan
 * @version 2019年07月31日
 * @since 2019年07月31日
 **/
@ConditionalOnProperty("redis.cache.enabled")
@Component
public class SpringBootApolloRefreshConfig {
    private static final Logger logger = LoggerFactory.getLogger(SpringBootApolloRefreshConfig.class);
    @Autowired
    @Qualifier("ApolloConfig")
    ConfigDto configDto;

    private final SampleRedisConfig sampleRedisConfig;
    private final RefreshScope refreshScope;

    public SpringBootApolloRefreshConfig(final SampleRedisConfig sampleRedisConfig, final RefreshScope refreshScope) {
        this.sampleRedisConfig = sampleRedisConfig;
        this.refreshScope = refreshScope;
    }

    @ApolloConfigChangeListener(value = {ConfigConsts.NAMESPACE_APPLICATION, "application.yml","suntek.console"})
    public void onChange(ConfigChangeEvent changeEvent) {
//        logger.debug("before refresh {}", sampleRedisConfig.toString());
        logger.debug("before refresh {}", configDto.toString());
//        refreshScope.refresh("sampleRedisConfig");
        refreshScope.refresh("ApolloConfig");
//        logger.debug("after refresh {}", sampleRedisConfig.toString());
        logger.debug("after refresh {}", configDto.toString());
    }
}

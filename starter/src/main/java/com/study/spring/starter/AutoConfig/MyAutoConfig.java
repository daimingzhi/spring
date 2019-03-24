package com.study.spring.starter.AutoConfig;

import com.study.spring.starter.service.MyService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dmz
 * @date Create in 21:24 2019/3/23
 */
@Configuration
@EnableConfigurationProperties(Config.class)
@ConditionalOnClass(MyService.class)
public class MyAutoConfig {

    @Bean
    @ConditionalOnMissingBean(MyService.class)
    public MyService getMyService() {
        return new MyService();
    }
}

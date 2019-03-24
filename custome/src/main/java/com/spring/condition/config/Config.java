package com.spring.condition.config;

import com.spring.condition.domain.Father;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: dmz
 * @Description:
 * @Date: Create in 0:44 2019/3/22
 */
@Configuration
@ComponentScan("com.spring.condition.domain")
@EnableConfigurationProperties(Father.class)
public class Config {

}

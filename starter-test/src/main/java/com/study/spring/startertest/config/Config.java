package com.study.spring.startertest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dmz
 * @date Create in 15:46 2019/3/26
 */
@ConfigurationProperties(prefix = "application")
@Data
@Configuration
public class Config {
    private String name;
    private String age;

    private final son son = new

            son();

    @Data
    public static class son {
        public String type;
    }

    @Bean
    public Model getModel() {
        return new Model();
    }
}

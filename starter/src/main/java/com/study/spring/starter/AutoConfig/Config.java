package com.study.spring.starter.AutoConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dmz
 * @date Create in 22:06 2019/3/23
 */
@Data
@ConfigurationProperties(prefix = "config")
public class Config {
    private String name;
    private Integer age;
}

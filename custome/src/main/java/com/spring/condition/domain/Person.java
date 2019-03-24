package com.spring.condition.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: dmz
 * @Description:
 * @Date: Create in 1:03 2019/3/22
 */
@ConfigurationProperties
@Data
@Component
public class Person {
    private String who;
}

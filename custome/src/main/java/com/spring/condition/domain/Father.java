package com.spring.condition.domain;

import lombok.Data;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: dmz
 * @Description:
 * @Date: Create in 0:46 2019/3/22
 */
@Data
@Component
@Conditional(FatherCondition.class)
@PropertySource("D:\\develop\\myProjects\\spring\\custome\\src\\main\\resources\\application.yml")
public class Father {
    private String who;

    public void say() {
        System.out.println("I am father ");
    }
}

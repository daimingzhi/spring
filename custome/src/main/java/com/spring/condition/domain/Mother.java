package com.spring.condition.domain;

import lombok.Data;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * @Author: dmz
 * @Description:
 * @Date: Create in 0:48 2019/3/22
 */
@Data
@Component
@Conditional(MotherCondition.class)
public class Mother {
    public void say() {
        System.out.println("i am mother");
    }
}

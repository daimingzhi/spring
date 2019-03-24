package com.study.springboot.condition.domain;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * @author dmz
 * @date Create in 20:45 2019/3/23
 */
@Component
@Conditional(CatCondition.class)
public class Cat{

    public Cat(){
        System.out.println("猫来了");
    }

}

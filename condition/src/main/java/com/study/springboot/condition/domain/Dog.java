package com.study.springboot.condition.domain;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * @author dmz
 * @date Create in 20:44 2019/3/23
 */
@Component
@Conditional(DogCondition.class)
public class Dog  {

    public Dog(){
        System.out.println("狗来了");
    }
}

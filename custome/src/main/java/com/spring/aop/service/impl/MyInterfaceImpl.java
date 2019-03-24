package com.spring.aop.service.impl;

import com.spring.aop.service.MyInterface;
import org.springframework.stereotype.Component;

/**
 * @author dmz
 * @date Create in 22:57 2019/3/16
 */
@Component
public class MyInterfaceImpl implements MyInterface {
    @Override
    public void add() {
        System.out.println("add方法加了个666");
    }
}

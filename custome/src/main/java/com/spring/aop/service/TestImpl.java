package com.spring.aop.service;

import org.springframework.stereotype.Component;

/**
 * @author dmz
 * @date Create in 23:14 2019/3/16
 */
@Component
public class TestImpl implements Test {
    @Override
    public void test(int a, int b) {
        System.out.println("test方法执行了");
    }
}

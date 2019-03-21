package com.spring.aop.service.impl;

import com.spring.aop.service.Test;
import org.springframework.stereotype.Component;

/**
 * @Author: dmz
 * @Description:
 * @Date: Create in 1:03 2019/3/17
 */
@Component
public class TestImpl implements Test {
    @Override
    public void test(int a, int b) {
        System.out.println("test方法执行了");
    }
}

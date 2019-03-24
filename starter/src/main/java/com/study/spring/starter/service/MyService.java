package com.study.spring.starter.service;

import com.study.spring.starter.AutoConfig.Config;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dmz
 * @date Create in 21:53 2019/3/23
 */
public class MyService {

    @Autowired
    private Config config;

    public void say() {
        System.out.println("自定义的starter来了,say:" + config.getName());
    }
}

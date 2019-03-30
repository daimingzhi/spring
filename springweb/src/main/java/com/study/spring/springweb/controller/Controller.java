package com.study.spring.springweb.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dmz
 * @date Create in 17:08 2019/3/29
 */
@RestController
@RequestMapping
public class Controller {
    public Controller(ApplicationContext applicationContext) {
        System.out.println("11111");
        System.out.println(applicationContext);
    }
}

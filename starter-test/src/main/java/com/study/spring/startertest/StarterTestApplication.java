package com.study.spring.startertest;

import com.study.spring.starter.service.MyService;
import com.study.spring.startertest.config.Config;
import com.study.spring.startertest.config.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StarterTestApplication implements ApplicationRunner {

    @Autowired
    private MyService myService;

    @Autowired
    private Config config;

    @Autowired
    private Model model;

    public static void main(String[] args) {
        SpringApplication.run(StarterTestApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String name = config.getName();
        String type = config.getSon().getType();
        System.out.println(type);
        System.out.println(model.getName());
        myService.say();
    }
}

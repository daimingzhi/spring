package com.study.spring.startertest;

import com.study.spring.starter.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StarterTestApplication implements ApplicationRunner {

    @Autowired
    private MyService myService;

    public static void main(String[] args) {
        SpringApplication.run(StarterTestApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        myService.say();
    }
}

package com.study.spring.springdatajpa;

import com.study.spring.springdatajpa.entity.User;
import com.study.spring.springdatajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.study.spring.springdatajpa.repository"})
public class SpringDataJpaApplication implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<User> all = userRepository.findAll();
        List<User> like = userRepository.findFirstByUseEmailLike("%.com");
        System.out.println(like);
    }
}

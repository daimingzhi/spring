package com.study.spring.transaction;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author daimzh
 */
@SpringBootApplication
@EnableTransactionManagement
public class TransactionApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}

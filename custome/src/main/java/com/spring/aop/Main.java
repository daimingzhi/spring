package com.spring.aop;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dmz
 * @date Create in 21:36 2019/3/16
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ProxyAop.class);
//        Test bean = applicationContext.getBean(Test.class);
////        Test01 bean1 = applicationContext.getBean(Test01.class);
////        Demo bean2 = applicationContext.getBean(Demo.class);
////        bean.test(20,60);;
////        bean1.test01();;
////        bean2.test();;
//        System.out.println(bean);
//        System.out.println(bean instanceof MyInterface);
//        MyInterface bean3 = (MyInterface) bean;
//        bean3.add();
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = format.format(date);
        System.out.println(time);
//        SpringApplication.run(Main.class);
    }
}

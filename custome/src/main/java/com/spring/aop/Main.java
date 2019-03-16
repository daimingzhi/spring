package com.spring.aop;

import com.spring.aop.config.EnhanceAop;
import com.spring.aop.service.MyInterface;
import com.spring.aop.service.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author dmz
 * @date Create in 21:36 2019/3/16
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(EnhanceAop.class);
        Test bean = applicationContext.getBean(Test.class);
//        Test01 bean1 = applicationContext.getBean(Test01.class);
//        Demo bean2 = applicationContext.getBean(Demo.class);
//        bean.test(20,60);;
//        bean1.test01();;
//        bean2.test();;
        MyInterface bean3 = (MyInterface) bean;
        bean3.add();
    }
}

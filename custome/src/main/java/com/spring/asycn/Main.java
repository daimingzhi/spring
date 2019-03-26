package com.spring.asycn;

import java.text.DecimalFormat;

/**
 * @Author: dmz
 * @Description:
 * @Date: Create in 0:23 2019/3/16
 */
public class Main {
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext applicationContext =
//                new AnnotationConfigApplicationContext(AsyncConfig.class);
//        SyncService bean = applicationContext.getBean(SyncService.class);
//        bean.test(1);
//        ThreadPoolTaskExecutor executor = applicationContext.getBean(ThreadPoolTaskExecutor.class);
////        ThreadPoolTaskExecutor bean1 =applicationContext.getBean()
////        bean1.shutdown();
//        executor.shutdown();
        Double pi = 13.1415926;
        System.out.println(new DecimalFormat("0.000").format(pi));//3.14
    }
}

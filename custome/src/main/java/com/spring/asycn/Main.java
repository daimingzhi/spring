package com.spring.asycn;

import com.spring.asycn.config.AsyncConfig;
import com.spring.asycn.service.SyncService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Author: dmz
 * @Description:
 * @Date: Create in 0:23 2019/3/16
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AsyncConfig.class);
        SyncService bean = applicationContext.getBean(SyncService.class);
        bean.test(1);
        ThreadPoolTaskExecutor executor = applicationContext.getBean(ThreadPoolTaskExecutor.class);
//        ThreadPoolTaskExecutor bean1 =applicationContext.getBean()
//        bean1.shutdown();
        executor.shutdown();

    }
}

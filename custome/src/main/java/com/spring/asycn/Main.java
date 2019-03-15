package com.spring.asycn;

import com.spring.asycn.config.AsyncConfig;
import com.spring.asycn.service.SyncService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        for (int i = 0; i < 100; i++) {
            bean.test(i);
        }
    }
}

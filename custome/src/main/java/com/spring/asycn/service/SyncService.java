package com.spring.asycn.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: dmz
 * @Description:
 * @Date: Create in 0:24 2019/3/16
 */
@Component
public class SyncService {
    @Async
    public  void test(int i) {
            System.out.println(Thread.currentThread().getName() + "执行方法______________"+i);
    }
}

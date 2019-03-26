package com.spring.asycn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: dmz
 * @Description:
 * @Date: Create in 0:24 2019/3/16
 */
@Component
@RequiredArgsConstructor
public class SyncService {

    private final Serivce serivce;

    @Async
    public void test(int i) {
        serivce.show();

        System.out.println(Thread.currentThread().getName() + "执行方法______________" + i);
    }
}

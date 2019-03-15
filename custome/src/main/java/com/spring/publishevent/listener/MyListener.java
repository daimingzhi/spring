package com.spring.publishevent.listener;

import com.spring.publishevent.event.MyEvent;
import com.spring.publishevent.event.MySecondEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Author: dmz
 * @Description:
 * @Date: Create in 0:23 2019/3/15
 */
@Component
public class MyListener{
    @EventListener
    public void onApplicationEvent(MyEvent event) {
        System.out.println(event.getMsg());
    }

    @EventListener
    public void onApplicationEvent(MySecondEvent event) {
        System.out.println(event.getMsg());
    }

}

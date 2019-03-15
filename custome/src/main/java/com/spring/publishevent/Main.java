package com.spring.publishevent;

import com.spring.publishevent.config.Config;
import com.spring.publishevent.event.MyEvent;
import com.spring.publishevent.event.MySecondEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: dmz
 * @Description:
 * @Date: Create in 0:32 2019/3/15
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(Config.class);
        applicationContext.publishEvent(new MyEvent("hello event"));
        applicationContext.publishEvent(new MySecondEvent("hello secondEvent"));
    }
}

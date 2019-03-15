package com.spring.publishevent.event;

/**
 * @Author: dmz
 * @Description:
 * @Date: Create in 0:20 2019/3/15
 */
public class MyEvent {

    private String msg;

    public MyEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}

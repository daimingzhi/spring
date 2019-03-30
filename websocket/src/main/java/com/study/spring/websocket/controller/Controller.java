package com.study.spring.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dmz
 * @date Create in 12:37 2019/3/30
 */
@RestController
public class Controller {

    @MessageMapping("/test")
    @SendTo("/topic/d1")
    public String test(String name) {
        return "hello webSocket," + name;
    }
}

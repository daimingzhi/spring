package com.spring.security.controller;

import com.spring.security.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dmz
 * @date Create in 22:26 2019/3/19
 */
@RestController
@RequestMapping("/test")
public class Controller {
    @RequestMapping("/login")
    public String login(User user) {
        if (user.getPassword().equals("123") && user.getUsername().equals("tom")) {
            return "登录成功";
        } else {
            return "登录失败";
        }
    }

    @RequestMapping("/view")
    public String view() {
        return "看一看";
    }
}

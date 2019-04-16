package com.weub.myboot.controller;

import com.weub.myboot.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/hello")
    public String index() {
        return "hello world";
    }

    @RequestMapping("user")
    public User user() {
        User user = new User();
        user.setFirstName("weub");
        user.setLastName("banana1");
        return user;
    }
}

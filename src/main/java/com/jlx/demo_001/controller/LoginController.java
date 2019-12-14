package com.jlx.demo_001.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LoginController {
    @RequestMapping("/login")
    public String loginJuege(String openid){
        System.out.println(openid);
        String a = "ok";
        return a;
    }
}

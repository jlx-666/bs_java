package com.jlx.demo_001.controller;

import com.jlx.demo_001.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController

public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public void loginJudge(String openid,String name,int type){
        userService.login(openid, name,type);
    }

    @RequestMapping("/saveUser")
    public void saveUser(String openid,String name){
        userService.saveUser(openid, name);
    }

    @RequestMapping("/getNameById")
    public String getNameById(String openid){
        return userService.getNameById(openid);
    }

    @RequestMapping("/getNameByMemberIds")
    public ArrayList<String> getNameByMemberIds(String memberIds){
        System.out.println(memberIds+"memberIds");
        return userService.getNameByMemberIds(memberIds);
    }

}

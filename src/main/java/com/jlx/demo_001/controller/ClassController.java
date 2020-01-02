package com.jlx.demo_001.controller;

import com.jlx.demo_001.pojo.ClassForStu;
import com.jlx.demo_001.server.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public  class ClassController {
    @Autowired
    ClassService classService;

    @RequestMapping("/addClass")
    public void addClass(String openid,String name){
        System.out.println(openid+"and"+name);
        classService.add(openid,name);
    }

    @RequestMapping("/getMyFound")
    public ArrayList<ClassForStu> findByOpenId( String openid){
        System.out.println(openid);
        return classService.findByOpenId(openid);
    }

    @RequestMapping("/getDetailById")
    public ClassForStu getDetailById(int id){
        System.out.println(id);
        return classService.getById(id);
    }

    @RequestMapping("/getMyJoin")
    public ArrayList<ClassForStu> getMyJoin(String openid){
        return classService.getJoin(openid);
    }

    @RequestMapping("/judgeWho")
    public String judgeWho(String openId,int classId){
        return  classService.judgeWho(openId,classId);
    }

    @RequestMapping("/joinClass")
    public void join(String openId,int classId){
        classService.join(openId,classId);
    }

    /*@RequestMapping("/getMemberIds")
    public ArrayList<String> getMember(int classId){
        return classService.getMember(classId);
    }*/


}

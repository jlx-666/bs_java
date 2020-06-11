package com.jlx.demo_001.controller;

import com.jlx.demo_001.pojo.Homework;
import com.jlx.demo_001.pojo.PaperBase;
import com.jlx.demo_001.service.HomeworkService;
import com.jlx.demo_001.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;


    @RequestMapping("/addHomework")
    public void addHomework(int classId,int paperId,int how_long){
        homeworkService.add(classId,paperId,how_long);
    }

    @RequestMapping("/findPersonalHomework")
    public ArrayList<Homework> findPersonalHomework(int classId,String openid){
        return homeworkService.findByClassIdAndOpenid(classId,openid);
    }
    @RequestMapping("/findPersonalHomeworkByState")
    public ArrayList<Homework> findPersonalHomeworkByState(String openid,String state){
        return homeworkService.findByOpenidAndState(openid,state);
    }

    @RequestMapping("/getHomeworkAnswer")
    public Map<Integer,String> getAnswer(int classId,String openId, int paperId){
        return homeworkService.getAnswer(classId,openId,paperId);
    }

    @RequestMapping("/getHomeworkById")
    public Homework getById(int classId,String openId, int paperId){
        return homeworkService.getHomeworkById(classId,openId,paperId);
    }

    @RequestMapping("/saveHomework")
    public void save(int classId,String openId, int paperId,String answer){
        homeworkService.save(classId,openId,paperId,answer);
    }

    @RequestMapping("/homeworkDone")
    public void homeworkDone(int classId,String openId, int paperId,String answer){
        homeworkService.homeworkDone(classId,openId,paperId,answer);
    }

    @RequestMapping("/changeSomeoneState")
    public void changeSomeoneState(String openid){
        homeworkService.changeSomeoneState(openid);
    }

    @RequestMapping("/getPaperidListByClassid")
    public ArrayList<Integer> getPaperidListByClassid(int classId){
        System.out.println(homeworkService.getPaperidListByClassid(classId));

        return homeworkService.getPaperidListByClassid(classId);

    }

    @RequestMapping("/changeStateByClassAndPaper")
    public void changeStateByClassAndPaper(int classId,int paperId){
        homeworkService.changeStateByClassAndPaper(classId,paperId);
    }

    @RequestMapping("/getClassPaperListByState")
    public ArrayList<PaperBase> getClassPaperListByState(int classId,String state){
        return homeworkService.getClassPaperListByState(classId,state);
    }

    @RequestMapping("/findHomeworkOfSomeoneInClassIdByState")
    public ArrayList<Homework> findHomeworkOfSomeoneInClassIdByState(int classId,String openId,String state){
        return homeworkService.findHomeByClassIdAndOpenidAndState(classId,openId,state);
    }

    @RequestMapping("/checkChoiceOfHomeworkClass")
    public Map<String,Integer> checkChoiceOfHomeworkClass(int classId,int paperId,int num){
        return homeworkService.checkChoiceOfHomeworkClass(classId,paperId,num);
    }

    @RequestMapping("/getRemainingTime")
    public ArrayList<Long> getRemainingTime(int classId,int paperId){
        return homeworkService.getRemainingTime(classId, paperId);
    }
}

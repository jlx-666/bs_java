package com.jlx.demo_001.controller;


import com.jlx.demo_001.pojo.PaperBase;
import com.jlx.demo_001.service.PaperService;
import com.jlx.demo_001.service.impl.CollectionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class CollectionController {
    @Autowired
    CollectionsServiceImpl collectionsService;
    @Autowired
    PaperService paperService;


    @RequestMapping("/addCollection")
    public void addCollection(String openId,int paperId){
        collectionsService.add(openId,paperId);
    }

    @RequestMapping("/judgeById")
    public boolean judgeById(String openId,int paperId){
        return collectionsService.judgeById(openId,paperId);
    }

    @RequestMapping("/saveCollection")
    public void saveCollection(String openId,int paperId,String answer){
        collectionsService.save(openId, paperId, answer);
    }

    @RequestMapping("/getAnswer")
    public Map<Integer,String> getAnswer(String openId,int paperId){
        Map map = collectionsService.getAnswer(openId, paperId);
        return map;
    }

    @RequestMapping("/dropCollection")
    public void dropCollection(String openId,int paperId){
        collectionsService.drop(openId, paperId);
    }

    @RequestMapping("/getMyCollections")                                              //与Paper模块耦合
    public ArrayList<PaperBase> getMyCollections(String openId){
        return paperService.findPaperBasesByCollections(collectionsService.getByOpenId(openId));
    }

}

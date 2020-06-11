package com.jlx.demo_001.controller;

import com.jlx.demo_001.pojo.Choice;
import com.jlx.demo_001.pojo.PaperBase;
import com.jlx.demo_001.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    PaperService paperService;

    @RequestMapping("/addChoice")
    public int addChoice(String title,String opA,String opB,String opC,String opD,String answer,double difficulty){
        return paperService.addChoice(title, opA, opB, opC, opD, answer, difficulty);
    }

    @RequestMapping("/findChoicePage")
    public List<Choice> findChoicePage(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return paperService.findChoicePage(pageable);
    }

    @RequestMapping("/getChoiceMaxPage")
    public long getChoiceMaxPage(int size){
        return paperService.getMaxPage(size);
    }

    @RequestMapping("/getPaperMaxPage")
    public long getPaperMaxPage(int size){
        return paperService.getMaxPaperPage(size);
    }

    @RequestMapping("/findPaperPage")
    public List<PaperBase> findPaperPage(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return paperService.findPaperPage(pageable);
    }

    @RequestMapping("getChoiceById")
    public Choice getChoiceById(int id){
        return paperService.getChoiceById(id);
    }

    @RequestMapping("saveChoice")
    public void saveChoice(int id,String title,String opA,String opB,String opC,String opD,String answer,double difficulty){
        Choice choice = paperService.getChoiceById(id);
        paperService.saveChoice(id,title, opA, opB, opC, opD, answer, difficulty);
    }
}

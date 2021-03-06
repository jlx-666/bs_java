package com.jlx.demo_001.controller;

import com.alibaba.fastjson.JSONObject;
import com.jlx.demo_001.DAO.PaperMarketRepository;
import com.jlx.demo_001.pojo.*;
import com.jlx.demo_001.service.CollectionService;
import com.jlx.demo_001.service.GA;
import com.jlx.demo_001.service.HomeworkService;
import com.jlx.demo_001.service.PaperService;
import com.jlx.demo_001.service.impl.CollectionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class PaperController {
    @Autowired
    GA ga;
    @Autowired
    PaperService paperService;
    @Autowired
    PaperMarketRepository paperMarketRepository;
    @Autowired
    HomeworkService homeworkService;
    @Autowired
    CollectionsServiceImpl collectionsService;


    @RequestMapping("/getGA")
    public Paper getGA(double difficulty,int countChoice){
        System.out.println(difficulty);
        Paper paper = ga.getPaperByGA(difficulty,countChoice);
        return paper;
    }


    @RequestMapping(value = "/savePaper", method = RequestMethod.POST )
    public int savePaper(@RequestBody JSONObject paperJson) {
        Paper paper = JSONObject.parseObject(paperJson.get("paper").toString(),Paper.class);
        PaperBase paperBase = paperService.changeIntoIdString(paper);
        PaperBase paperBase1 = paperMarketRepository.save(paperBase);
        System.out.println("id"+paperBase1.getId());
        return paperBase1.getId();
    }

    @RequestMapping("/getPaperIds")
    public ArrayList<PaperBase> getPaperInfo(int size){
        return paperService.getPaperIds(size);
    }

    @RequestMapping("/getPaperInfoByCollections")
    public ArrayList<PaperBase> getPaperInfoByCollections(ArrayList<Collection> collections){
        return null;
    }

    @RequestMapping("/getById")
    public Paper getPaperById(int id){
        PaperBase paperBase = paperMarketRepository.findById(id).get();
        Paper paper = paperService.paperBaseChangeIntoPaper(paperBase);
        paper.setId(id);
        return paper;
    }

    @RequestMapping("/findAnswerByPaperId")
    public Map<Integer,String> findAnswerByPaperId(int paperId){
        return paperService.findAnswerByPaperId(paperId);
    }

    @RequestMapping("/deletePaperById")
    public void deletePaperById(int paperId){
        homeworkService.deleteHomeworkByPaperId(paperId);
        collectionsService.dropByPaperId(paperId);
        paperService.deleteById(paperId);
    }
}

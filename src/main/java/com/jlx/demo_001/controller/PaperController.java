package com.jlx.demo_001.controller;

import com.alibaba.fastjson.JSONObject;
import com.jlx.demo_001.DAO.PaperMarketRepository;
import com.jlx.demo_001.pojo.*;
import com.jlx.demo_001.server.GA;
import com.jlx.demo_001.server.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class PaperController {
    @Autowired
    GA ga;
    @Autowired
    PaperService paperService;
    @Autowired
    PaperMarketRepository paperMarketRepository;


    @RequestMapping("/getGA")
    public Paper getGA(double difficulty){
        System.out.println(difficulty);
        Paper paper = ga.getPaperByGA(difficulty);
        return paper;
    }


    @RequestMapping(value = "/savePaper", method = RequestMethod.POST )
    public void savePaper(@RequestBody JSONObject paperJson) {
        Paper paper = JSONObject.parseObject(paperJson.get("paper").toString(),Paper.class);
        PaperBase paperBase = paperService.changeIntoIdString(paper);
        paperMarketRepository.save(paperBase);
        System.out.println("save success");
    }

    @RequestMapping("/getPaperIds")
    public ArrayList<PaperBase> getPaperInfo(){
        ArrayList<PaperBase> paperBases = new ArrayList<>();
        for (int i =0;i<10;i++){
            int id = 1002+i;
            System.out.println(id);
            PaperBase paperBase = paperMarketRepository.findById(id).get();
            paperBases.add(paperBase);
        }
        return  paperBases;
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

}

package com.jlx.demo_001.controller;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jlx.demo_001.DAO.PaperMarketRepository;
import com.jlx.demo_001.pojo.Choice;
import com.jlx.demo_001.pojo.Paper;
import com.jlx.demo_001.pojo.PaperBase;
import com.jlx.demo_001.pojo.WordProblem;
import com.jlx.demo_001.server.GA;
import com.jlx.demo_001.until.PaperUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class GetPaper {
    @Autowired
    GA ga;
    @Autowired
    PaperUtil paperUtil;
    @Autowired
    PaperMarketRepository paperMarketRepository;


    @RequestMapping("/getGA")
    public Paper getGA(double difficulty){
        System.out.println(difficulty);
        Paper paper = ga.getPaperByGA(difficulty);
        paperMarketRepository.save(paperUtil.changeIntoIdString(paper));
        return paper;
    }


    @RequestMapping("/savePaper")
    public Choice savePaper(@RequestBody JSONObject paper) {
        String a = paper.toJSONString();


        System.out.println("a"+StringEscapeUtils.unescapeJava(a));
        Choice paper1 = JSONObject.parseObject(a,new TypeReference<Choice>(){});

        System.out.println("Json"+paper1.toString());

        //System.out.println("对象"+paper1.toString());
        return paper1;
        /*PaperBase paperBase;
        paperBase = paperUtil.changeIntoIdString(paper);
        paperMarketRepository.save(paperBase);*/
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
        System.out.println(paperBases);
        return  paperBases;
    }

    @RequestMapping("/getById")
    public Paper getPaperById(int id){
        PaperBase paperBase = paperMarketRepository.findById(id).get();
        Paper paper = paperUtil.paperBaseChangeIntoPaper(paperBase);
        return paper;
    }
}

package com.jlx.demo_001.controller;

import com.jlx.demo_001.pojo.Paper;
import com.jlx.demo_001.server.GA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetPaperByGA {
    @Autowired
    GA ga;
    @RequestMapping("/getGA")
    public Paper getGA(double difficulty){
        System.out.println(difficulty);
        return ga.getPaperByGA(difficulty);
    }
}

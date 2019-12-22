package com.jlx.demo_001;

import com.jlx.demo_001.DAO.*;
import com.jlx.demo_001.server.impl.CollectionsServiceImpl;
import com.jlx.demo_001.server.impl.GAImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Random;

@SpringBootTest
public class DemoSbApplicationTests {
    @Autowired
    private  PaperMarketRepository paperMarketRepository;
    @Autowired
    private BlanksRepository br ;
    @Autowired
    private ChoiceRepository cr;
    @Autowired
    private WordProblemRepository wr;
    @Autowired
    private CollectionsRepository collectionsRepository;
    @Autowired
    GAImpl ga;
    @Autowired
    CollectionsServiceImpl collectionsService;


    @Test
    void contextLoads() {

        //System.out.println(collectionsRepository.findCollectionsByOpenId("o1PFH4-_5S71Wp4swBpfAMMPn40A"));
        Map<Integer,String> map = collectionsService.getAnswer("o1PFH4-_5S71Wp4swBpfAMMPn40A",1010);
        System.out.println(map);
        map.put(9,"l");
        System.out.println(map);
        /* PaperUtil paperUtil = new PaperUtil();
        Choice c = new Choice();
        for (int i=0;i<10;i++){
            double difficulty = getRandom();
        Paper paper = ga.getPaperByGA(difficulty);
        paperMarketRepository.save(paperUtil.changeIntoIdString(paper));}
       for(int i=1;i<=1000;i++){
            c = cr.findById(i).get();
            c.setTitle("这是第"+i+"题");
            c.setOpA("选项A");
            c.setOpB("选项B");
            c.setOpC("选项C");
            c.setOpD("选项D");
            cr.save(c);
        }
        System.out.println("OK");*/
        //System.out.println(cr.findById(1001));

     //paperUtil.countMaxChoice();
       // System.out.println(paperUtil.changeIntoIdString(ga.getPaperByGA(0.65)).toString());

        //int[] i = getRandomId("blanks");
        //System.out.println(cr.countMaxChoice());


    }


   /* void addRandomChoice(){
        Choice c = new Choice();
        c.setDifficulty(getRandom());
        cr.save(c);
    }
    void addRandomBlanks(){
        Blanks b = new Blanks();
        b.setCountBlanks(1);
        b.setDifficulty(getRandom());
        br.save(b);

    }
    void addRandomWordProblem(){
        WordProblem w = new WordProblem();
        w.setDifficult(getRandom());
        wr.save(w);
    }*/
    double getRandom(){
        Random rand=new Random();
        int i = (int)(Math.random()*10+50);
        double num = i/100.0;
        return num;
    }


}

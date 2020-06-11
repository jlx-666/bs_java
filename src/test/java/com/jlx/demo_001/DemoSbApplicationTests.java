package com.jlx.demo_001;

import com.jlx.demo_001.DAO.*;
import com.jlx.demo_001.controller.PaperController;
import com.jlx.demo_001.pojo.Choice;
import com.jlx.demo_001.pojo.Paper;
import com.jlx.demo_001.service.ClassService;
import com.jlx.demo_001.service.HomeworkService;
import com.jlx.demo_001.service.PaperService;
import com.jlx.demo_001.service.impl.CollectionsServiceImpl;
import com.jlx.demo_001.service.impl.GAImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    ClassService classService;
    @Autowired
    Class_stuRepository class_stuRepository;
    @Autowired
    HomeworkRepository homeworkRepository;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    PaperService paperService;
    @Autowired
    HomeworkService homeworkService;
    @Autowired
    PaperController paperController;

    @Test
    void contextLoads() {
        //System.out.println(collectionsRepository.findCollectionsByOpenId("o1PFH4-_5S71Wp4swBpfAMMPn40A"));
        //System.out.println(findNumberIn2DArray(nums,5));

        //Date date = new Date();
        //date.setTime(8640000000L);
      System.out.println(paperService.getMaxPage(1));

       /*for(int i=1;i<=5000;i++){
           Choice c = new Choice();
           String answer;
            double t = getRandom();
            if(t<0.25) answer = "A";
            else if(t>=0.25&t<0.5) answer = "B";
            else if(t>=0.5&t<0.75) answer = "C";
            else answer = "D";
            c.setAnswer(answer);
            c.setDifficulty(getRandom());
            c.setTitle("这是第"+i+"题");
            c.setOpA("选项A");
            c.setOpB("选项B");
            c.setOpC("选项C");
            c.setOpD("选项D");
            cr.save(c);
            System.out.println("ID:"+c.getId());
        }
        System.out.println("OK");*/
        //System.out.println(cr.findById(1001));

     //paperUtil.countMaxChoice();
       // System.out.println(paperUtil.changeIntoIdString(ga.getPaperByGA(0.65)).toString());

        //int[] i = getRandomId("blanks");
        //System.out.println(cr.countMaxChoice());
        //redisTemplate.opsForValue().set("key","123");
        //System.out.println(redisTemplate.opsForValue().get("key"));;

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
        int i = (int)(Math.random()*100);
        double num = i/100.0;
        return num;
    }
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int maxRow = matrix.length;
        int maxCol = matrix[0].length;
        int i = maxRow-1;int j = 0;
        do{
            if(target>matrix[i][j]) j++;
            if(target<matrix[i][j]) i--;
            if(target==matrix[i][j]) return true;
            System.out.println(i+","+j);
        }while(j<maxCol&&i>=0);
        return false;
    }
    int[][] nums = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};

    public void a(){
        Choice c = new Choice();
        for (int i=0;i<20;i++){
            double difficulty = getRandom();
            //Paper paper = ga.getPaperByGA(difficulty);
            //paperMarketRepository.save(paperService.changeIntoIdString(paper));
        }
    }
}

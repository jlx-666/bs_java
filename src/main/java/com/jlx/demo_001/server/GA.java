package com.jlx.demo_001.server;

import com.jlx.demo_001.pojo.Blanks;
import com.jlx.demo_001.pojo.Choice;
import com.jlx.demo_001.pojo.Paper;
import com.jlx.demo_001.pojo.WordProblem;

import java.util.ArrayList;

public interface GA {
    public static int maxTurn = 500;                            //最大迭代数
    public static int population = 30;                          //种群规模
    public static int countChoice = 10;                         //试卷选择题数量
    public static int countBlanks = 15;                         //试卷填空题数量
    public static int countWordProblem = 5;                     //试卷应用题数量
    public static double pCross = 0.25;                         //交叉基因选择概率
    public static double pV = 0.01;                             //变异概率

    public abstract ArrayList<Paper> init();                    //生成试卷种群
    public abstract ArrayList<Paper> eliminate();               //根据适应度进行淘汰
    public abstract Paper getPaperByGA(Double difficult);            //通过交叉，变异等操作筛选出目标试卷
    public abstract ArrayList<Paper> crossing();                //交叉
    public abstract ArrayList<Paper> variation();               //变异








    /*
    *@param:paper
    *@return:double
     */
    public abstract double countFitness(Paper paper);



}

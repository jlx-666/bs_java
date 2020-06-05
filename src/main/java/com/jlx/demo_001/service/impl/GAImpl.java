package com.jlx.demo_001.service.impl;

import com.jlx.demo_001.DAO.BlanksRepository;
import com.jlx.demo_001.DAO.ChoiceRepository;
import com.jlx.demo_001.DAO.WordProblemRepository;
import com.jlx.demo_001.pojo.Blanks;
import com.jlx.demo_001.pojo.Choice;
import com.jlx.demo_001.pojo.Paper;
import com.jlx.demo_001.pojo.WordProblem;
import com.jlx.demo_001.service.GA;
import com.jlx.demo_001.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.*;

@Service
public class GAImpl implements GA {
    @Autowired
    private BlanksRepository blanksRepository;
    @Autowired
    private ChoiceRepository choiceRepository;
    @Autowired
    private WordProblemRepository wordProblemRepository;
    @Autowired
    PaperService paperService;
    private double aimDifficulty;
    private ArrayList<Paper> papers;
    private int countChoice;

    @Override
    public Paper getPaperByGA(Double difficult,int countchoice) {
        countChoice = countchoice;
        long startTime;long endTime;
        aimDifficulty = difficult;
        Paper bestOne = null;
        int turn = 0;
        startTime = System.currentTimeMillis();
        papers = init();
        process:
        for(turn = 0;turn<maxTurn;turn++){
            for (Paper p:papers){
                if(countFitness(p)==1){
                    bestOne = p;
                    bestOne.setFitness(countFitness(bestOne));
                    endTime = System.currentTimeMillis();
                    System.out.println("本次查找迭代"+turn+"次"+"\n用时"+(endTime-startTime)+"ms");
                    break process;
                }
            }
            papers = eliminate();
            papers = crossing();
            papers = variation();
        }
        if(bestOne==null){
            Map<Double,Integer> map = new HashMap<>();
            ArrayList<Double> fit = new ArrayList<>();
            int k=0;
            for (Paper p:papers){
                double pFit = countFitness(p);
                fit.add(pFit);
                map.put(pFit,k);
                k++;
            }
            double maxFit = Collections.max(fit);
            bestOne = papers.get(map.get(maxFit));
            bestOne.setFitness(countFitness(bestOne));
            endTime = System.currentTimeMillis();
            System.out.println(bestOne.getFitness());
            System.out.println(bestOne.getFitness()*aimDifficulty);
            System.out.println("本次查找迭代"+turn+"次"+"\n用时"+(endTime-startTime)+"ms");
        }
        double d = paperService.countDifficulty(bestOne);
        BigDecimal bg = new BigDecimal(d);
        double difficulty = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        bestOne.setDifficulty(difficulty);
        System.out.println(difficulty+"diff");
        System.out.println(bestOne);
        return bestOne;
    }

    @Override
    public ArrayList<Paper> init() {
        papers = new ArrayList<Paper>();
        for (int i=0;i<population*2;i++) {
            ArrayList<Choice> choices = getChoice();
            ArrayList<Blanks> blanks = getBlanks();
            ArrayList<WordProblem> wordProblems = getWordProblem();
            Paper paper = new Paper(choices,blanks,wordProblems);
            paper.setFitness(countFitness(paper));
            papers.add(paper);
        }
        //System.out.println(papers);
        return papers;
    }

    @Override
    public ArrayList<Paper> eliminate() {
        ArrayList<Paper> newPopulation = new ArrayList<>();
        double[] allFitness = new double[population*2];
        Set<Integer> newPopulationNum= new HashSet<>();
        double countAlFit = 0.00;
        for (Paper p : papers){
            int i = 0;
            allFitness[i] = p.getFitness();
            countAlFit+=p.getFitness();
            i++;
        }
        do{
            Random r = new Random();
            double pointer = r.nextDouble()*countAlFit;
            double rollAt = 0.00;
            roll:
            for (int i = 0; i < allFitness.length; i++) {
                rollAt += papers.get(i).getFitness();
                if(rollAt>pointer){
                    newPopulationNum.add(i);
                    break roll;
                }
            }
        }while (newPopulationNum.size()<population);
        for(int num:newPopulationNum){
            newPopulation.add(papers.get(num));
        }
        papers.clear();
        for(Paper p:newPopulation){
            papers.add(p);
        }
        return papers;
    }

    @Override
    public ArrayList<Paper> crossing() {
        ArrayList<Integer> paperId = new ArrayList<>();
        ArrayList<Paper> temp = new ArrayList<>();
        temp = papers;
        for(int i=1;i<=population;i++){
            paperId.add(i);
        }
        Collections.shuffle(paperId);                       //将paperId打乱排序
        for (int i=0;i<paperId.size()-1;i+=2){              //将打乱排序后的paperId按序进行两两交叉
            temp.add(changeChromosome(papers.get(i),papers.get(i+1)));
            temp.add(changeChromosome(papers.get(i+1),papers.get(i)));
        }
        return temp;
    }

    @Override
    public ArrayList<Paper> variation() {
        ArrayList<Integer> whereVariation = new ArrayList<>();
        ArrayList<Integer> newId = new ArrayList<>();
        ArrayList<Paper> varPaper = new ArrayList<>();
        for(Paper p:papers){
            /*
            **遍历选择题进行变异
             */
            ArrayList<Choice> choices = p.getChoices();
            int i = 0;                                      //表示数组下标
            for(Choice c:choices){
                newId.add(c.getId());
                if(judgeVariation()){
                    whereVariation.add(i);
                }
            }
            for (int j:whereVariation){
                newId.set(j,-1);
                boolean varOK = false;
                for (;!varOK;){
                    int varId = choiceRepository.findRandomId();
                    if(!newId.contains(varId)){
                        newId.set(j,varId);
                        choices.set(j,choiceRepository.findById(varId).get());
                        varOK = true;
                    }
                }
            }
            p.setChoices(choices);
            whereVariation.clear();
            newId.clear();
            /*
             **遍历填空题进行变异
             */
            ArrayList<Blanks> blanks = p.getBlanks();
            i = 0;                                      //表示数组下标
            for(Blanks b:blanks){
                newId.add(b.getId());
                if(judgeVariation()){
                    whereVariation.add(i);
                }
            }
            for (int j:whereVariation){
                newId.set(j,-1);
                boolean varOK = false;
                for (;!varOK;){
                    int varId = getOneRandomId("blanks");
                    if(!newId.contains(varId)){
                        newId.set(j,varId);
                        blanks.set(j,blanksRepository.findById(varId).get());
                        varOK = true;
                    }
                }
            }
            p.setBlanks(blanks);
            whereVariation.clear();
            newId.clear();

            /*
             **遍历应用题进行变异
             */
            ArrayList<WordProblem> wordProblems = p.getWordProblems();
            i = 0;                                      //表示数组下标
            for(WordProblem w:wordProblems){
                newId.add(w.getId());
                if(judgeVariation()){
                    whereVariation.add(i);
                }
            }
            for (int j:whereVariation){
                newId.set(j,-1);
                boolean varOK = false;
                for (;!varOK;){
                    int varId = getOneRandomId("wordProblem");
                    if(!newId.contains(varId)){
                        newId.set(j,varId);
                        wordProblems.set(j,wordProblemRepository.findById(varId).get());
                        varOK = true;
                    }
                }
            }
            p.setWordProblems(wordProblems);
            whereVariation.clear();
            newId.clear();
            varPaper.add(p);
        }
        return varPaper;
    }


    private ArrayList<Choice> getChoice() {
        int count = choiceRepository.countMaxChoice();
        int length = countChoice;
        Set<Integer> ids = new HashSet();
        int z=0;
        while(ids.size()<length){
            System.out.println(choiceRepository.findRandomId());
            z++;
           ids.add(choiceRepository.findRandomId());
        }
        System.out.println("次数："+z);
        ArrayList<Choice> choices = new ArrayList<>();
        for(int i:ids){
          Choice c = choiceRepository.findById(i).get();
          choices.add(c);
        }
        return choices;
    }


    public ArrayList<Blanks> getBlanks() {
        ArrayList<Blanks> blanks = new ArrayList<>();
        int[] id = getRandomId("blanks");
        for (int i:id){
            Blanks b = blanksRepository.findById(i).get();
            blanks.add(b);
        }
        return blanks;
    }


    public ArrayList<WordProblem> getWordProblem() {
        ArrayList<WordProblem> wordProblems = new ArrayList<>();
        int[] id = getRandomId("wordProblem");
        for (int i:id){
            WordProblem w = wordProblemRepository.findById(i).get();
            wordProblems.add(w);
        }
        return wordProblems;
    }

    public Paper changeChromosome(Paper paper1,Paper paper2){        //交叉具体操作
        Paper son = new Paper();
        Map<Integer,Object> map = new HashMap<>();
        Set<Integer> sonId = new HashSet<>();
        int i=0;int j=0;
        /*
        **选择题进行交叉
         */
        ArrayList<Choice> sonChoice = new ArrayList<>();
        for(Choice c:paper1.getChoices()){
            Random r = new Random();
            double p = r.nextDouble();
            if(p<=pCross){
              sonId.add(c.getId());
              sonChoice.add(c);
            }
        }
        ArrayList<Choice> temp = paper2.getChoices();
        Collections.shuffle(temp);
        i=0;                                            //temp下标
        j=0;                                        //map下标
        while (sonId.size()<countChoice){
            if(!sonId.contains(temp.get(i).getId())) {
                sonId.add(temp.get(i).getId());
                map.put(j,temp.get(i));
                j++;
            }
            i++;
        }
        for (int k=0;k<map.size();k++) {
            sonChoice.add((Choice) map.get(k));
        }
        son.setChoices(sonChoice);
        map.clear();
        sonId.clear();

        /*
        **填空题交叉
         */
        ArrayList<Blanks> sonBlanks = new ArrayList<>();

        for(Blanks b:paper1.getBlanks()){
            Random r = new Random();
            double p = r.nextDouble();
            if(p<=pCross){
                sonId.add(b.getId());
                sonBlanks.add(b);
            }
        }
        ArrayList<Blanks> tempB = paper2.getBlanks();
        Collections.shuffle(tempB);
        i=0;                                            //temp下标
        j=0;                                        //map下标
        while (sonId.size()<countBlanks){
            if(!sonId.contains(tempB.get(i).getId())) {
                sonId.add(tempB.get(i).getId());
                map.put(j,tempB.get(i));
                j++;
            }
            i++;
        }
        for (int k=0;k<map.size();k++) {
            sonBlanks.add((Blanks) map.get(k));
        }
        son.setBlanks(sonBlanks);
        map.clear();
        sonId.clear();
        /*
         **应用题交叉
         */
        ArrayList<WordProblem> sonWP = new ArrayList<>();

        for(WordProblem w:paper1.getWordProblems()){
            Random r = new Random();
            double p = r.nextDouble();
            if(p<=pCross){
                sonId.add(w.getId());
                sonWP.add(w);
            }
        }
        ArrayList<WordProblem> tempW = paper2.getWordProblems();
        Collections.shuffle(tempW);
        i=0;                                            //temp下标
        j=0;                                        //map下标
        while (sonId.size()<countWordProblem){
            if(!sonId.contains(tempW.get(i).getId())) {
                sonId.add(tempW.get(i).getId());
                map.put(j,tempW.get(i));
                j++;
            }
            i++;
        }
        for (int k=0;k<map.size();k++) {
            sonWP.add((WordProblem) map.get(k));
        }
        son.setWordProblems(sonWP);
        return son;
    }

    @Override
    public double countFitness(Paper paper) {
        double difficulty = 0.00;
        ArrayList<Choice> choices = paper.getChoices();
        ArrayList<Blanks> blanks = paper.getBlanks();
        ArrayList<WordProblem> wordProblems = paper.getWordProblems();
        for (Choice c:choices){
            difficulty += c.getDifficulty()*Choice.number;
        }
        for (Blanks b : blanks){
            difficulty += b.getDifficulty()*Blanks.number;
        }
        for (WordProblem w : wordProblems){
            difficulty += w.getDifficult()*WordProblem.number;
        }
        return 1-(Math.abs(choices.size()*Choice.number*aimDifficulty-difficulty)/choices.size()*Choice.number*aimDifficulty);
    }

    private int getOneRandomId(String type){
        int count=0;
        //System.out.println("-------------------------------");
        switch (type){
            case "choice":
                count = choiceRepository.countMaxChoice();
                break;
            case "blanks":
                count = blanksRepository.countMaxBlanks();
                break;
            case "wordProblem":
                count = wordProblemRepository.countMaxWordProblem();
                break;
        }
        Random r = new Random();
        int id = r.nextInt((count-1))+1;
        return id;
    }
    /*
    **@param String 题型
    **@return int[] 该题型随机id数组
     */
    private int[] getRandomId(String type){
        int count=0;
        int length=0;
        //System.out.println("-------------------------------");
        switch (type){
            case "choice":
                count = choiceRepository.countMaxChoice();
                length = countChoice;
            break;
            case "blanks":
                count = blanksRepository.countMaxBlanks();
                length = countBlanks;
                break;
            case "wordProblem":
                count = wordProblemRepository.countMaxWordProblem();
                length = countWordProblem;
                break;
        }
        int[] idArr = new int[length];
        Set<Integer> ids = new HashSet();
        while(ids.size()<length){
            Random r = new Random();
            ids.add(r.nextInt((count-1))+1);
        }
        int n = 0;
        for (int i:ids){
            //System.out.println(i);
            idArr[n]=i;
            n++;
        }
        return idArr;
    }

    private boolean judgeVariation(){
        Random r = new Random();
        double pv = r.nextDouble();
        if(pv<pV){
            return true;
        }
        else return false;
    }

}

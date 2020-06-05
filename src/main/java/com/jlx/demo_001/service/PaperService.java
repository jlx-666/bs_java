package com.jlx.demo_001.service;

import com.jlx.demo_001.DAO.BlanksRepository;
import com.jlx.demo_001.DAO.ChoiceRepository;
import com.jlx.demo_001.DAO.PaperMarketRepository;
import com.jlx.demo_001.DAO.WordProblemRepository;
import com.jlx.demo_001.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaperService {
    @Autowired
    PaperMarketRepository paperMarketRepository;
    @Autowired
    ChoiceRepository choiceRepository;
    @Autowired
    BlanksRepository blanksRepository;
    @Autowired
    WordProblemRepository wordProblemRepository;

    public PaperBase changeIntoIdString(Paper paper){
        String choiceIdString,blanksIdString,wordProblemIdString;
        StringBuffer idTemp = new StringBuffer();
        for(Choice c:paper.getChoices()){
            idTemp.append(c.getId());
            idTemp.append(",");
        }
        choiceIdString = idTemp.toString();
        idTemp.setLength(0);
        for(Blanks b:paper.getBlanks()){
            idTemp.append(b.getId());
            idTemp.append(",");
        }
        blanksIdString = idTemp.toString();
        idTemp.setLength(0);
        for(WordProblem w:paper.getWordProblems()){
            idTemp.append(w.getId());
            idTemp.append(",");
        }
        wordProblemIdString = idTemp.toString();
        DecimalFormat df = new DecimalFormat("#.00");
        double difficulty = Double.parseDouble(df.format(countDifficulty(paper)));
        PaperBase pb = new PaperBase("标题",choiceIdString,blanksIdString,wordProblemIdString,difficulty);
        return pb;
    }

    public Paper paperBaseChangeIntoPaper(PaperBase paperBase){
        String[] choiceId,blankId,wpId;
        ArrayList<Choice> choices = new ArrayList<>();
        ArrayList<Blanks> blanks = new ArrayList<>();
        ArrayList<WordProblem> wordProblems = new ArrayList<>();
        Paper paper = new Paper();
        choiceId = paperBase.getChoiceId().split(",");
        for (String cId:choiceId){
            int id = Integer.parseInt(cId.trim());
            Choice c = choiceRepository.findById(id).get();
            choices.add(c);
        }
        /*blankId = paperBase.getBlanksId().split(",");
        for (String bId:blankId){
            int id = Integer.parseInt(bId.trim());
            Blanks b = blanksRepository.findById(id).get();
            blanks.add(b);
        }
        wpId = paperBase.getWordProblemsId().split(",");
        for (String wId:wpId){
            int id = Integer.parseInt(wId.trim());
            WordProblem w = wordProblemRepository.findById(id).get();
            wordProblems.add(w);
        }*/
        paper.setChoices(choices);
        paper.setBlanks(blanks);
        paper.setWordProblems(wordProblems);
        paper.setDifficulty(paperBase.getDifficulty());
        paper.setTitle(paperBase.getTitle());
        return paper;
    }


    public double countDifficulty(Paper paper){
        double difficulty = 0.00;
        ArrayList<Choice> choices = paper.getChoices();
        ArrayList<Blanks> blanks = paper.getBlanks();
        ArrayList<WordProblem> wordProblems = paper.getWordProblems();
        for (Choice c:choices){
            difficulty += c.getDifficulty();
        }
        for (Blanks b : blanks){
            difficulty += b.getDifficulty()*Blanks.number;
        }
        for (WordProblem w : wordProblems){
            difficulty += w.getDifficult()*WordProblem.number;
        }
        System.out.println("111"+difficulty);
        return difficulty/(choices.size());
    }

    public ArrayList<PaperBase> findPaperBasesByCollections(ArrayList<Collection> collections){
        ArrayList<PaperBase> paperBases = new ArrayList<>();
        for(Collection c : collections){
            if(paperMarketRepository.findById(c.getPaperId()).isPresent()) {
                PaperBase paperBase = paperMarketRepository.findById(c.getPaperId()).get();
                paperBases.add(paperBase);
            }
        }
        return paperBases;
    }

    public Map<Integer,String> findAnswerByPaperId(int paperId){
        Map<Integer,String> answer = new HashMap<>();
        ArrayList<String> answerList = new ArrayList<>();
        String[] choiceId;
        PaperBase paperBase = paperMarketRepository.findById(paperId).get();
        choiceId = paperBase.getChoiceId().split(",");
        int key=1;
        for (String c : choiceId){
            int id = Integer.parseInt(c.trim());
            answer.put(key,choiceRepository.findById(id).get().getAnswer().trim());
            key++;
        }
        System.out.println("paperanswer"+answer);
        return answer;
    }
}

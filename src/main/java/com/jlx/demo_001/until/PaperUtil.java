package com.jlx.demo_001.until;

import com.jlx.demo_001.DAO.BlanksRepository;
import com.jlx.demo_001.DAO.ChoiceRepository;
import com.jlx.demo_001.DAO.WordProblemRepository;
import com.jlx.demo_001.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PaperUtil {
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

        PaperBase pb = new PaperBase("标题",choiceIdString,blanksIdString,wordProblemIdString,countDifficulty(paper));
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
        blankId = paperBase.getBlanksId().split(",");
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
        }
        paper.setChoices(choices);
        paper.setBlanks(blanks);
        paper.setWordProblems(wordProblems);
        paper.setDifficulty(paperBase.getDifficulty());
        return paper;
    }


    public double countDifficulty(Paper paper){
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
        return difficulty/100;
    }
}

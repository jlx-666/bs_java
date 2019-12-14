package com.jlx.demo_001.until;

import com.jlx.demo_001.pojo.*;

import java.util.ArrayList;

public class PaperUtil {
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

        PaperBase pb = new PaperBase("",choiceIdString,blanksIdString,wordProblemIdString,countDifficulty(paper));
        return pb;
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

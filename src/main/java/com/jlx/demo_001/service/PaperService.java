package com.jlx.demo_001.service;

import com.jlx.demo_001.DAO.BlanksRepository;
import com.jlx.demo_001.DAO.ChoiceRepository;
import com.jlx.demo_001.DAO.PaperMarketRepository;
import com.jlx.demo_001.DAO.WordProblemRepository;
import com.jlx.demo_001.pojo.*;
import com.jlx.demo_001.pojo.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

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

    public int addChoice(String title,String opA,String opB,String opC,String opD,String answer,double difficulty){
        Choice choice = new Choice();
        choice.setDifficulty(difficulty);
        choice.setAnswer(answer);
        choice.setTitle(title);
        choice.setOpA(opA);
        choice.setOpB(opB);
        choice.setOpC(opC);
        choice.setOpD(opD);
        Choice c = choiceRepository.save(choice);
        return c.getId();
    }
    public int saveChoice(int id,String title,String opA,String opB,String opC,String opD,String answer,double difficulty){
        Choice choice = new Choice();
        choice.setId(id);
        choice.setDifficulty(difficulty);
        choice.setAnswer(answer);
        choice.setTitle(title);
        choice.setOpA(opA);
        choice.setOpB(opB);
        choice.setOpC(opC);
        choice.setOpD(opD);
        Choice c = choiceRepository.save(choice);
        return c.getId();
    }

    public List<Choice> findChoicePage(Pageable pageable) {
        Page<Choice> page = choiceRepository.findAll(pageable);
        return page.getContent();
    }

    public long getMaxPage(int size){
        long max = choiceRepository.count();
        if(max%size!=0){
            return max/size+1;
        }
        else return max/size;
    }

    public long getMaxPaperPage(int size){
        long max = paperMarketRepository.count();
        if(max%size!=0){
            return max/size+1;
        }
        else return max/size;
    }

    public List<PaperBase> findPaperPage(Pageable pageable) {
        Page<PaperBase> page = paperMarketRepository.findAll(pageable);
        return page.getContent();
    }

    public ArrayList<PaperBase> getPaperIds(int size){
        ArrayList<PaperBase> ids = new ArrayList<>();
        Set<PaperBase> set = new HashSet<>();
        for (;set.size()<size;){
            PaperBase paperBase = paperMarketRepository.getRandomPaper();
            set.add(paperBase);
        }
        for (PaperBase paperBase:set){
            paperBase.setTitle("自动组卷习题"+paperBase.getId());
            DecimalFormat df = new DecimalFormat("#.00");
            double difficulty = Double.parseDouble(df.format(paperBase.getDifficulty()));
            paperBase.setDifficulty(difficulty);
            ids.add(paperBase);
        }
        return ids;
    }

    public Choice getChoiceById(int id){
        return choiceRepository.findById(id).get();
    }

    public void deleteById(int paperId){
        ArrayList<PaperBase> paperBases = paperMarketRepository.findAllById(paperId);
        paperMarketRepository.deleteAll(paperBases);
    }
}

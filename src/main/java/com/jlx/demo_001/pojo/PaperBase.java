package com.jlx.demo_001.pojo;

import javax.persistence.*;

@Entity
public class PaperBase {

    @Id

    int id;
    @Column
    private String title;
    @Column
    private String choiceId;
    @Column
    private String blanksId;
    @Column
    private String wordProblemsId;
    @Column
    private double difficulty;

    public PaperBase() {
    }

    public PaperBase(String title, String choiceId, String blanksId, String wordProblemsId, double difficulty) {
        this.title = title;
        this.choiceId = choiceId;
        this.blanksId = blanksId;
        this.wordProblemsId = wordProblemsId;
        this.difficulty = difficulty;
    }

    public PaperBase(int id, String title, String choiceId, String blanksId, String wordProblemsId, double difficulty) {
        this.id = id;
        this.title = title;
        this.choiceId = choiceId;
        this.blanksId = blanksId;
        this.wordProblemsId = wordProblemsId;
        this.difficulty = difficulty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(String choiceId) {
        this.choiceId = choiceId;
    }

    public String getBlanksId() {
        return blanksId;
    }

    public void setBlanksId(String blanksId) {
        this.blanksId = blanksId;
    }

    public String getWordProblemsId() {
        return wordProblemsId;
    }

    public void setWordProblemsId(String wordProblemsId) {
        this.wordProblemsId = wordProblemsId;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "标题:     "         +title+
                "\n选择题ID\n" + choiceId +
                "\n填空题ID\n" + blanksId +
                "\n应用题ID\n" + wordProblemsId +
                "\n试卷难度："  + difficulty +
                '}';
    }
}

package com.jlx.demo_001.pojo;



import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;

public class Paper {
    private  int id;
    @JSONField(name = "title")
    private String title;
    @JSONField(name = "choices")
    private ArrayList<Choice> choices;
    @JSONField(name = "blanks")
    private ArrayList<Blanks> blanks;
    @JSONField(name = "wordProblems")
    private ArrayList<WordProblem> wordProblems;
    @JSONField(name = "fitness")
    private double fitness;
    @JSONField(name = "difficulty")
    private double difficulty;

    public static int countChoice = 10;                         //试卷选择题数量
    public static int countBlanks = 15;                         //试卷填空题数量
    public static int countWordProblem = 5;                     //试卷应用题数量


    public Paper() {
    }

    public Paper(ArrayList<Choice> choices, ArrayList<Blanks> blanks, ArrayList<WordProblem> wordProblems) {
        this.choices = choices;
        this.blanks = blanks;
        this.wordProblems = wordProblems;
    }

    public Paper(ArrayList<Choice> choices, ArrayList<Blanks> blanks, ArrayList<WordProblem> wordProblems, double fitness) {
        this.choices = choices;
        this.blanks = blanks;
        this.wordProblems = wordProblems;
        this.fitness = fitness;
    }

    public Paper(ArrayList<Choice> choices, ArrayList<Blanks> blanks, ArrayList<WordProblem> wordProblems, double fitness, double difficulty) {
        this.choices = choices;
        this.blanks = blanks;
        this.wordProblems = wordProblems;
        this.fitness = fitness;
        this.difficulty = difficulty;
    }

    public Paper(int id, ArrayList<Choice> choices, ArrayList<Blanks> blanks, ArrayList<WordProblem> wordProblems, double fitness, double difficulty) {
        this.id = id;
        this.choices = choices;
        this.blanks = blanks;
        this.wordProblems = wordProblems;
        this.fitness = fitness;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<Choice> choices) {
        this.choices = choices;
    }

    public ArrayList<Blanks> getBlanks() {
        return blanks;
    }

    public void setBlanks(ArrayList<Blanks> blanks) {
        this.blanks = blanks;
    }

    public ArrayList<WordProblem> getWordProblems() {
        return wordProblems;
    }

    public void setWordProblems(ArrayList<WordProblem> wordProblems) {
        this.wordProblems = wordProblems;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
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
                "\n选择题\n" + choices +
                "\n填空题\n" + blanks +
                "\n应用题\n" + wordProblems +
                "\n适应度：" + fitness +
                '}';
    }
}

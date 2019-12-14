package com.jlx.demo_001.pojo;

import java.util.ArrayList;

public class Paper {
    private ArrayList<Choice> choices;
    private ArrayList<Blanks> blanks;
    private ArrayList<WordProblem> wordProblems;
    private double fitness;
    private double difficulty;


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

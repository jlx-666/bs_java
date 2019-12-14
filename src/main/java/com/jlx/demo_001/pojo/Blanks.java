package com.jlx.demo_001.pojo;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

@Entity
public class Blanks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String title;
    @Column(nullable = false)
    private double difficulty;
    @Column(nullable = false)
    private int countBlanks;
    @Column
    private String answer;
    public static double number = 2.00;

    public Blanks() {

    }

    public Blanks(String title, double difficulty, int countBlanks, String answer) {
        this.title = title;
        this.difficulty = difficulty;
        this.countBlanks = countBlanks;
        this.answer = answer;
    }

    public Blanks(int id, String title, double difficulty, int countBlanks, String answer) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
        this.countBlanks = countBlanks;
        this.answer = answer;
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

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public int getCountBlanks() {
        return countBlanks;
    }

    public void setCountBlanks(int countBlanks) {
        this.countBlanks = countBlanks;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return
                "题目id:" + id +
                        ", 难度:" + difficulty  + "        "
                ;
    }
}

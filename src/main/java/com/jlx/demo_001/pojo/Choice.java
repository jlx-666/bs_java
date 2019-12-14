package com.jlx.demo_001.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Choice {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private double difficulty;
    @Column
    private String title;
    @Column
    private String opA;
    @Column
    private String opB;
    @Column
    private String opC;
    @Column
    private String opD;
    @Column
    private String answer;
    public static double number = 2.00;

    public Choice() {
    }

    public Choice(double difficulty, String title, String opA, String opB, String opC, String opD, String answer) {
        this.difficulty = difficulty;
        this.title = title;
        this.opA = opA;
        this.opB = opB;
        this.opC = opC;
        this.opD = opD;
        this.answer = answer;
    }

    public Choice(int id, double difficulty, String title, String opA, String opB, String opC, String opD, String answer) {
        this.id = id;
        this.difficulty = difficulty;
        this.title = title;
        this.opA = opA;
        this.opB = opB;
        this.opC = opC;
        this.opD = opD;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOpA() {
        return opA;
    }

    public void setOpA(String opA) {
        this.opA = opA;
    }

    public String getOpB() {
        return opB;
    }

    public void setOpB(String opB) {
        this.opB = opB;
    }

    public String getOpC() {
        return opC;
    }

    public void setOpC(String opC) {
        this.opC = opC;
    }

    public String getOpD() {
        return opD;
    }

    public void setOpD(String opD) {
        this.opD = opD;
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

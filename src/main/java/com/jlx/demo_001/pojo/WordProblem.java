package com.jlx.demo_001.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;


@Entity
public class WordProblem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JSONField(name = "id")
    private int id;
    @Column
    @JSONField(name = "title")
    private String title;
    @Column
    @JSONField(name = "difficult")
    private double difficult;
    public static double number = 10.00;

    public WordProblem() {
    }

    public WordProblem(String title, double difficult) {
        this.title = title;
        this.difficult = difficult;
    }

    public WordProblem(int id, String title, double difficult) {
        this.id = id;
        this.title = title;
        this.difficult = difficult;
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

    public double getDifficult() {
        return difficult;
    }

    public void setDifficult(double difficult) {
        this.difficult = difficult;
    }

    @Override
    public String toString() {
        return
                "题目id:" + id +
                        ", 难度:" + difficult  + "        "
                ;
    }
}

package com.jlx.demo_001.pojo;

import com.jlx.demo_001.pojo.primaryKey.CollectionKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(CollectionKey.class)
public class Collection {
    @Id
    private String openId;
    @Id
    private int paperId;
    @Column
    private String answer;

    public Collection() {
    }

    public Collection(String openId, int paperId, String answer) {
        this.openId = openId;
        this.paperId = paperId;
        this.answer = answer;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Collections{" +
                "openId='" + openId + '\'' +
                ", paperId=" + paperId +
                ", answer='" + answer + '\'' +
                '}';
    }
}


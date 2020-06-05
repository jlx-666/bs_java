package com.jlx.demo_001.pojo;

import com.jlx.demo_001.pojo.primaryKey.HomeworkKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;


@Entity
@IdClass(HomeworkKey.class)
public class Homework {
    @Id
    private int classId;
    @Id
    private String openid;
    @Id
    private int paper_id;
    @Column
    private String answer;
    @Column
    private String state;
    @Column
    private long start;
    @Column
    private long how_long;


    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getHow_long() {
        return how_long;
    }

    public void setHow_long(long how_long) {
        this.how_long = how_long;
    }

    public int getClass_id() {
        return classId;
    }

    public void setClass_id(int classId) {
        this.classId = classId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public int getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "classId=" + classId +
                ", openid='" + openid + '\'' +
                ", paper_id=" + paper_id +
                ", answer='" + answer + '\'' +
                ", state='" + state + '\'' +
                ", start=" + start +
                ", how_long=" + how_long +
                '}';
    }
}

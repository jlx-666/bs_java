package com.jlx.demo_001.pojo.primaryKey;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class HomeworkKey implements Serializable {
    @Id
    @Column
    private int classId;
    @Id
    @Column
    private String openid;
    @Id
    @Column
    private int paper_id;

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
}

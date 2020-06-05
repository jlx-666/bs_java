package com.jlx.demo_001.pojo;

import com.jlx.demo_001.pojo.primaryKey.Class_stuKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(Class_stuKey.class)
public class Class_stu {
    @Id
    private int classId;
    @Id
    private String openid;
    @Column
    private double grade;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int class_id) {
        this.classId = class_id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}

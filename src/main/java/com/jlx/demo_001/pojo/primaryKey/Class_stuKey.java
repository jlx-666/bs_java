package com.jlx.demo_001.pojo.primaryKey;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class Class_stuKey implements Serializable {
    @Id
    @Column
    private String openid;
    @Id
    @Column
    private int classId;


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}

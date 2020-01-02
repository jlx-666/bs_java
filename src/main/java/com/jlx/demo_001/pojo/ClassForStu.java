package com.jlx.demo_001.pojo;

import com.jlx.demo_001.pojo.primaryKey.Class_stuKey;

import javax.persistence.*;

@Entity
public class ClassForStu {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @Column
    private String masterId;
    @Column
    private String pic_path;

    public ClassForStu() {
    }

    public ClassForStu(String name, String master_id) {
        this.name = name;
        this.masterId = master_id;
    }

    public ClassForStu(int id, String name, String masterId, String pic_path) {
        this.id = id;
        this.name = name;
        this.masterId = masterId;
        this.pic_path = pic_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }
}

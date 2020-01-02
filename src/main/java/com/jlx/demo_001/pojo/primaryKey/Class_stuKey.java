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
    private int class_id;

    public String getOpenId() {
        return openid;
    }

    public void setOpenId(String openId) {
        this.openid = openId;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }
}

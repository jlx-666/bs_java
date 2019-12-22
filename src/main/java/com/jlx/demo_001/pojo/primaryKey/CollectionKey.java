package com.jlx.demo_001.pojo.primaryKey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

public class CollectionKey implements Serializable {
    @Id
    @Column
    private String openId;
    @Id
    @Column
    private int paperId;

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
}

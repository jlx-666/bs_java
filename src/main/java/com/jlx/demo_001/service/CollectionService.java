package com.jlx.demo_001.service;

import com.jlx.demo_001.pojo.Collection;

import java.util.ArrayList;

public interface CollectionService {
    public ArrayList<Collection> getByOpenId(String openId);
    public Collection getOneById(String openId, int paperId);
    public void save(String openId, int paperId,String answer);
    public void add(String openId,int paperId);
}

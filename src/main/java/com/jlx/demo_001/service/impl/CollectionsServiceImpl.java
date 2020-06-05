package com.jlx.demo_001.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jlx.demo_001.DAO.CollectionsRepository;
import com.jlx.demo_001.DAO.PaperMarketRepository;
import com.jlx.demo_001.pojo.Collection;
import com.jlx.demo_001.pojo.Paper;
import com.jlx.demo_001.pojo.PaperBase;
import com.jlx.demo_001.pojo.primaryKey.CollectionKey;
import com.jlx.demo_001.service.CollectionService;
import com.jlx.demo_001.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CollectionsServiceImpl implements CollectionService {
    @Autowired
    CollectionsRepository collectionsRepository;
    @Autowired
    PaperService paperService;
    @Autowired
    PaperMarketRepository paperMarketRepository;


    @Override
    public ArrayList<Collection> getByOpenId(String openId) {
        return collectionsRepository.findCollectionsByOpenId(openId);
    }

    @Override
    public Collection getOneById(String openId, int paperId) {
        CollectionKey collectionKey = new CollectionKey();
        collectionKey.setOpenId(openId);
        collectionKey.setPaperId(paperId);
        Collection collection = collectionsRepository.findById(collectionKey).get();
        return collection;
    }

    public Map<Integer,String> getAnswer(String openId,int paperId){
        Map<Integer,String> answer = new HashMap<>();
        Collection collection = getOneById(openId,paperId);
        String answerJson = collection.getAnswer();
        Map<String,String> map = JSONObject.parseObject(answerJson,Map.class);
        for(String strKey: map.keySet()){
            String value = map.get(strKey.trim());
            int key = Integer.parseInt(strKey.trim());
            answer.put(key,value);
        }
        return answer;
    }

    public boolean judgeById(String openId,int paperId) {
        CollectionKey collectionKey = new CollectionKey();
        collectionKey.setOpenId(openId);
        collectionKey.setPaperId(paperId);
        if((collectionsRepository.findById(collectionKey)).toString()!="Optional.empty") {
            return true;
        }
        else return false;
    }
    @Override
    public void save(String openId, int paperId,String answer) {
        Collection collection = new Collection();
        collection.setOpenId(openId);
        collection.setPaperId(paperId);
        collection.setAnswer(answer);
        collectionsRepository.save(collection);
    }

    @Override
    public void add(String openId, int paperId) {
        Collection collection = new Collection();
        collection.setOpenId(openId);
        collection.setPaperId(paperId);
        Map<String,Object> map = new HashMap<>();
        map.put("0","notnull");
        JSONObject json = new JSONObject(map);
        String answer = json.toJSONString();
        collection.setAnswer(answer);
        collectionsRepository.save(collection);
    }

    public void drop(String openId, int paperId){
        CollectionKey collectionKey = new CollectionKey();
        collectionKey.setOpenId(openId);
        collectionKey.setPaperId(paperId);
        collectionsRepository.deleteById(collectionKey);
    }
    public void saveAutoPaper(JSONObject paperJson,String openid){
        Paper paper = JSONObject.parseObject(paperJson.get("paper").toString(),Paper.class);
        PaperBase paperBase = paperMarketRepository.save(paperService.changeIntoIdString(paper));
        add(openid,paperBase.getId());
    }
}

package com.jlx.demo_001.service;

import com.alibaba.fastjson.JSONObject;
import com.jlx.demo_001.DAO.Class_stuRepository;
import com.jlx.demo_001.DAO.HomeworkRepository;
import com.jlx.demo_001.pojo.Class_stu;
import com.jlx.demo_001.pojo.Homework;
import com.jlx.demo_001.pojo.primaryKey.HomeworkKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class HomeworkService {
    @Autowired
    Class_stuRepository class_stuRepository;
    @Autowired
    HomeworkRepository homeworkRepository;

    public void add(int classId,int paperId,long how_long ){
        ArrayList<Homework> homeworkArrayList = new ArrayList<>();
        long start = System.currentTimeMillis();
        long how_longMS = how_long*60*1000;
        Map<String,Object> map = new HashMap<>();
        map.put("0","notnull");
        JSONObject json = new JSONObject(map);
        String answer = json.toJSONString();
        ArrayList<Class_stu> stu = class_stuRepository.findClass_stusByClassId(classId);
        for(Class_stu cs : stu){
            Homework homework = new Homework();
            homework.setPaper_id(paperId);
            homework.setClass_id(classId);
            homework.setAnswer(answer);
            String openid = cs.getOpenid();
            homework.setOpenid(openid);
            homework.setStart(start);
            homework.setHow_long(how_longMS);
            homework.setState("未完成");
            homeworkArrayList.add(homework);
        }
        System.out.println(stu);
        homeworkRepository.saveAll(homeworkArrayList);
    }

    public ArrayList<Homework> findByClassIdAndOpenid(int classId,String openid){
        return homeworkRepository.findAllByClassIdAndOpenid(classId,openid);
    }

    public ArrayList<Homework> findByOpenidAndState(String openid,String state){
        System.out.println(homeworkRepository.findAllByOpenidAndState(openid, state)); //aaaaaaaaaaaaaaaaaaaaa
        return homeworkRepository.findAllByOpenidAndState(openid, state);
    }
    public Map<Integer,String> getAnswer(int classId,String openId, int paperId){
        Map<Integer,String> answer = new HashMap<>();
        HomeworkKey homeworkKey = new HomeworkKey();
        homeworkKey.setClass_id(classId);
        homeworkKey.setOpenid(openId);
        homeworkKey.setPaper_id(paperId);
        Homework homework = homeworkRepository.findById(homeworkKey).get();
        String answerJson = homework.getAnswer();
        System.out.println(answerJson);
        Map<String,String> map = JSONObject.parseObject(answerJson,Map.class);
        for(String strKey: map.keySet()){
            String value = map.get(strKey.trim());
            int key = Integer.parseInt(strKey.trim());
            answer.put(key,value);
        }
        return answer;
    }

    public Homework getHomeworkById(int classId,String openId, int paperId){
        System.out.println(paperId);
        HomeworkKey homeworkKey = new HomeworkKey();
        homeworkKey.setClass_id(classId);
        homeworkKey.setOpenid(openId);
        homeworkKey.setPaper_id(paperId);
        return homeworkRepository.findById(homeworkKey).get();
    }

    public void save(int classId,String openId, int paperId,String answer) {
        Homework homework = getHomeworkById(classId,openId,paperId);
        homework.setClass_id(classId);
        homework.setPaper_id(paperId);
        homework.setOpenid(openId);
        homework.setAnswer(answer);
        homeworkRepository.save(homework);
    }

    public void homeworkDone(int classId,String openId, int paperId,String answer) {
        Homework homework = getHomeworkById(classId,openId,paperId);
        homework.setClass_id(classId);
        homework.setPaper_id(paperId);
        homework.setOpenid(openId);
        homework.setState("已完成");
        homework.setAnswer(answer);
        homeworkRepository.save(homework);
    }

    public void changeSomeoneState(String openId){
        ArrayList<Homework> notCheckTime = homeworkRepository.findAllByOpenidAndState(openId,"未完成");
        long now = System.currentTimeMillis();
        if(notCheckTime.size()!=0) {
            for (Homework h : notCheckTime) {
                if (now - h.getStart() >= h.getHow_long()) {
                    h.setState("已完成");
                    homeworkRepository.save(h);
                }
            }
        }
    }

    public ArrayList<Integer> getPaperidListByClassid(int classId){
        return homeworkRepository.getPaperidListByClassid(classId);
    }

    public void changeStateByClassAndPaper(int classId,int paperId){
        ArrayList<Homework> homeworkList = homeworkRepository.findAllByClassIdAndPaper_id(classId,paperId);
        ArrayList<Homework> homeworkOver = new ArrayList<>();
        for(Homework h : homeworkList){
            h.setState("已完成");
            homeworkOver.add(h);
        }
        homeworkRepository.saveAll(homeworkOver);
    }

    public boolean judgeHomeworkOverOrNot(int classId,int paperId){
        ArrayList<Homework> notOverList = homeworkRepository.findAllByClassIdAndPaper_idAndState(classId,paperId,"未完成");
        long now = System.currentTimeMillis();
        if(notOverList.size()==0){
            return true;
        }else {
            Homework h = notOverList.get(0);
            if (now - h.getStart() >= h.getHow_long()) {
                changeStateByClassAndPaper(classId,paperId);
                return true;
            }else {
                return false;
            }
        }
    }

    public ArrayList<Integer> getClassPaperListByState(int classId,String state){
        ArrayList<Integer> over = new ArrayList<>();
        ArrayList<Integer> notOver = new ArrayList<>();
        ArrayList<Integer> all = homeworkRepository.getPaperidListByClassid(classId);
        for (int paperId : all){
            if(judgeHomeworkOverOrNot(classId,paperId)){
                over.add(paperId);
            }else {
                notOver.add(paperId);
            }
        }
        if(state.trim().equals("已完成")){
            return over;
        }else {
            return notOver;
        }
    }

    public ArrayList<Homework> findHomeByClassIdAndOpenidAndState(int classId,String openId,String state){
        return homeworkRepository.findAllByClassIdAndOpenidAndState(classId,openId,state);
    }

    public Map<String,Integer> checkChoiceOfHomeworkClass(int classId,int paperId,int num){
        ArrayList<Homework> homeworkList = homeworkRepository.findAllByClassIdAndPaper_id(classId,paperId);
        Map<String,Integer> count = new HashMap<>();
        count.put("A",0);
        count.put("B",0);
        count.put("C",0);
        count.put("D",0);
        count.put("NaN",0);
        for (Homework h:homeworkList){
            Map<Integer,String> answer = new HashMap<>();
            String answerJson = h.getAnswer();
            Map<String,String> map = JSONObject.parseObject(answerJson,Map.class);
            for(String strKey: map.keySet()){
                String value = map.get(strKey.trim());
                int key = Integer.parseInt(strKey.trim());
                answer.put(key,value);
            }
            System.out.println("answer"+answer);
            if(answer.get(num)!=null) {
                count.put(answer.get(num), count.get(answer.get(num)) + 1);
            }else {
                count.put("NaN",count.get("NaN") + 1);
            }
        }
        return count;
    }

    public ArrayList<Long> getRemainingTime(int classId,int paperId){
        ArrayList<Homework> homeworkList = homeworkRepository.findAllByClassIdAndPaper_id(classId,paperId);
        Homework homework = homeworkList.get(0);
        long time = homework.getHow_long()+ homework.getStart()-System.currentTimeMillis();
        long days = (time / (1000 * 60 * 60 * 24));
        long hours = (time % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (time % (1000 * 60 * 60)) / (1000 * 60);
        ArrayList<Long> timeList = new ArrayList<>();
        timeList.add(days);
        timeList.add(hours);
        timeList.add(minutes);
        return timeList;
    }
}

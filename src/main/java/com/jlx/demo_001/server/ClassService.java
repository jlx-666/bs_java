package com.jlx.demo_001.server;

import com.jlx.demo_001.DAO.ClassRepository;
import com.jlx.demo_001.DAO.Class_stuRepository;
import com.jlx.demo_001.pojo.ClassForStu;
import com.jlx.demo_001.pojo.Class_stu;
import com.jlx.demo_001.pojo.primaryKey.Class_stuKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassService {
    @Autowired
    ClassRepository classRepository;
    @Autowired
    Class_stuRepository class_stuRepository;

    public void add(String openid,String name){
        ClassForStu classForStu = new ClassForStu();
        classForStu.setMasterId(openid);
        classForStu.setName(name);
        classRepository.save(classForStu);
    }

    public void join(String openid,int class_id){
        Class_stu class_stu = new Class_stu();
        class_stu.setClass_id(class_id);
        class_stu.setOpenid(openid);
        class_stuRepository.save(class_stu);
    }

    public String judgeWho(String openId,int classId){
        Class_stuKey class_stuKey = new Class_stuKey();
        class_stuKey.setClass_id(classId);
        class_stuKey.setOpenId(openId);
        if(classRepository.findById(classId).get().getMasterId().trim().equals(openId)){
            return "teacher";
        }else if(class_stuRepository.findById(class_stuKey).isPresent()){
            return "student";
        }else return "notJoin";
    }

    public ArrayList<ClassForStu> findByOpenId(String openId){
        ArrayList<ClassForStu> myFound = new ArrayList<>();
        myFound = classRepository.findClassForStusByMasterId(openId);
        return myFound;
    }

    public ClassForStu getById(int id){
        return classRepository.findById(id).get();
    }

    public ArrayList<ClassForStu> getJoin(String openid){
        ArrayList<Class_stu> relations = class_stuRepository.findClass_stusByOpenid(openid);
        ArrayList<Integer> idList = new ArrayList<>();
        for (Class_stu cs: relations){
            idList.add(cs.getClass_id());
        }
        return classRepository.findClassForStusByIdIn(idList);
    }

   /* public ArrayList<String> getMember(int classId){
        ArrayList<Class_stu> class_stus = class_stuRepository.findClass_stusByClass_id(classId);
        ArrayList<String> member = new ArrayList<>();
        for (Class_stu cs:class_stus){
            member.add(cs.getOpenid());
        }
        return member;
    }*/

}

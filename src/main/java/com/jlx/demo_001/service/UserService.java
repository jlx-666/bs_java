package com.jlx.demo_001.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jlx.demo_001.DAO.UsersRepository;
import com.jlx.demo_001.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UsersRepository userRepository;

    public int login(String openid,String name){
        String fistOrNot = userRepository.findById(openid).toString();
        if(fistOrNot!="Optional.empty"){
            System.out.println("不是第一次登录");
            Users users = userRepository.findById(openid).get();
            userRepository.save(users);
        }else{
            Users users = new Users();
            users.setOpenid(openid);
            users.setName(name);
            users.setType(0);
            userRepository.save(users);
        }
        return 1;
    }

    public void saveUser(String openid,String name){
        Users users = new Users();
        users.setOpenid(openid);
        users.setName(name);
        userRepository.save(users);
    }

    public String getNameById(String openid){
        return userRepository.findById(openid).get().getName();
    }

    public ArrayList<String> getNameByMemberIds(String memberIds){
        ArrayList<String> member =new ArrayList<>();
        JSONArray obj = JSON.parseArray(memberIds);
        for (int i=0;i<obj.size();i++){
            member.add(userRepository.findById((String) obj.get(i)).get().getName());
        }
        return member;
    }

    public int getTypeById(String openid){
        Users user = userRepository.findById(openid).get();
        return user.getType();
    }
}

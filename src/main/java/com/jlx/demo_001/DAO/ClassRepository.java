package com.jlx.demo_001.DAO;

import com.jlx.demo_001.pojo.ClassForStu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ClassRepository extends JpaSpecificationExecutor<ClassForStu>,CrudRepository<ClassForStu,Integer> {
    ArrayList<ClassForStu> findClassForStusByMasterId(String openId);
    ArrayList<ClassForStu> findClassForStusByIdIn(ArrayList<Integer> idList);
}

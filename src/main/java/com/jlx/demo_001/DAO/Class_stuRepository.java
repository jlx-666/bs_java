package com.jlx.demo_001.DAO;

import com.jlx.demo_001.pojo.Class_stu;
import com.jlx.demo_001.pojo.primaryKey.Class_stuKey;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface Class_stuRepository extends JpaSpecificationExecutor<Class_stu>,CrudRepository<Class_stu, Class_stuKey> {
    ArrayList<Class_stu> findClass_stusByOpenid(String openid);
    ArrayList<Class_stu> findClass_stusByClassId(int classId);
}

package com.jlx.demo_001.DAO;

import com.jlx.demo_001.pojo.Homework;
import com.jlx.demo_001.pojo.primaryKey.HomeworkKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface HomeworkRepository extends JpaSpecificationExecutor<Homework>, CrudRepository<Homework, HomeworkKey> {
    ArrayList<Homework> findAllByOpenid(String openid);
    ArrayList<Homework> findAllByClassId(int class_id);
    ArrayList<Homework> findAllByClassIdAndOpenid(int classId,String openid);
    ArrayList<Homework> findAllByOpenidAndState(String openid,String state);
    ArrayList<Homework> findAllByClassIdAndOpenidAndState(int classId,String openId,String state);
    @Query(nativeQuery=true,value = "select * from homework where class_id = ?1 and paper_id = ?2 and state = ?3")
    ArrayList<Homework> findAllByClassIdAndPaper_idAndState(int classId,int paperId,String state);
    @Query(nativeQuery=true,value = "select * from homework where class_id = ?1 and paper_id = ?2")
    ArrayList<Homework> findAllByClassIdAndPaper_id(int classId,int paperId);
    @Query(nativeQuery=true,value = "select * from homework where paper_id = ?1")
    ArrayList<Homework> findAllByPaperId(int paperId);
    @Query(nativeQuery=true,value = "select distinct paper_id from homework where class_id = ?1 ")
    ArrayList<Integer> getPaperidListByClassid(int classId);
    @Query(nativeQuery=true,value = "select distinct paper_id from homework where class_id = ?1 and state = ?2")
    ArrayList<Integer> getPaperidListByClassidAndState(int classId,String state);


}

package com.jlx.demo_001.DAO;

import com.jlx.demo_001.pojo.Choice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ChoiceRepository extends CrudRepository<Choice,Integer> {
    @Query(nativeQuery = true,value = "select MAX(id) from choice")
    int countMaxChoice();
    @Query(nativeQuery = true,value = "select MIN(id) from choice")
    int countMinChoice();
    @Query(nativeQuery=true, value="SELECT id FROM choice WHERE id >= (SELECT floor(RAND()*((SELECT MAX(id) FROM choice)-(SELECT MIN(id) FROM choice)+1))+(SELECT MIN(id) FROM choice)) ORDER BY id LIMIT 1" )
    int test();
    @Query(nativeQuery=true, value="SELECT id FROM choice WHERE id =(SELECT floor(RAND()*((SELECT MAX(id) FROM choice)-(SELECT MIN(id) FROM choice))+1517))" )
    int test2();
    @Query(nativeQuery=true, value="SELECT id FROM choice ORDER BY RAND() LIMIT 1" )
    int findRandomId();
    Page<Choice> findAll(Pageable pageable);
}

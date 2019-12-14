package com.jlx.demo_001.DAO;

import com.jlx.demo_001.pojo.Choice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ChoiceRepository extends CrudRepository<Choice,Integer> {
    @Query(nativeQuery = true,value = "select MAX(id) from choice")
    int countMaxChoice();

}

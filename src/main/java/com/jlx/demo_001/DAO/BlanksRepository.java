package com.jlx.demo_001.DAO;

import com.jlx.demo_001.pojo.Blanks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BlanksRepository extends CrudRepository<Blanks, Integer> {
    @Query(nativeQuery = true,value = "select MAX(id) from blanks")
    int countMaxBlanks();

}

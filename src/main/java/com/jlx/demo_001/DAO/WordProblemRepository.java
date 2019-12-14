package com.jlx.demo_001.DAO;

import com.jlx.demo_001.pojo.WordProblem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WordProblemRepository extends CrudRepository<WordProblem,Integer> {
    @Query(value = "select MAX(id) from word_problem",nativeQuery = true)
    int countMaxWordProblem();
}

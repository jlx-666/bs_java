package com.jlx.demo_001.DAO;

import com.jlx.demo_001.pojo.PaperBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PaperMarketRepository extends CrudRepository<PaperBase,Integer>, JpaSpecificationExecutor<PaperBase> {
    ArrayList<PaperBase> findAllById(int paperId);
    Page<PaperBase> findAll(Pageable pageable);
    @Query(nativeQuery=true, value="SELECT * FROM paper_base ORDER BY RAND() LIMIT 1" )
    PaperBase getRandomPaper();

}

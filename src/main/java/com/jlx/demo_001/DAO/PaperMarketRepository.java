package com.jlx.demo_001.DAO;

import com.jlx.demo_001.pojo.PaperBase;
import org.springframework.data.repository.CrudRepository;

public interface PaperMarketRepository extends CrudRepository<PaperBase,Integer> {
}

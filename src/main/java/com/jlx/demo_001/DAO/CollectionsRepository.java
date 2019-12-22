package com.jlx.demo_001.DAO;

import com.jlx.demo_001.pojo.Collection;
import com.jlx.demo_001.pojo.primaryKey.CollectionKey;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CollectionsRepository extends JpaSpecificationExecutor<Collection>, CrudRepository<Collection, CollectionKey> {
    ArrayList<Collection> findCollectionsByOpenId(String openId);
}

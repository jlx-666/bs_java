package com.jlx.demo_001.DAO;


import com.jlx.demo_001.pojo.Users;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends JpaSpecificationExecutor<Users>, CrudRepository<Users, String> {

}

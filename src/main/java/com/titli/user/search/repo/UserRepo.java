package com.titli.user.search.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.titli.user.search.entity.User;

@Repository
/*public interface UserRepo extends CrudRepository<User, Integer> {

}*/

public interface UserRepo extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

}
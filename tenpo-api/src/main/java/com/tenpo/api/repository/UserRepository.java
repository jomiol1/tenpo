package com.tenpo.api.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tenpo.api.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    
	Optional<UserEntity> findByUsernameAndPassword(String username, String pwd);
	
	Optional<UserEntity> findByUsername(String username);
}

package com.usk.demo.repository;

import com.usk.demo.entity.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserInfo,String>{

	Optional<UserInfo> findByEmailId(String email);
	
	@Query(value = "{ 'userId': ?0 }", fields = "{ 'userId': 1, 'userName': 1, 'password': 1, 'firstName': 1, 'lastName': 1, 'age': 1, 'gender': 1, 'EmailId': 1, 'mobileNo':1 }")
	Optional<UserInfo> findByUserId(String string);

	Optional<UserInfo> findByUserName(String name);


	

}

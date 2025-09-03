package com.usk.demo.repository;

import com.usk.demo.entity.UserAccountNumber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AccountNoRepository extends MongoRepository<UserAccountNumber,Long> {

	Optional<UserAccountNumber> findByAccountNumber(Long fromAcc);

	@Query(value = "{ 'userId': ?0 }", fields="{'accountNumber':1, 'balance':1, 'userId':1}")
	Optional<UserAccountNumber> findByUserId(Long userId);

	
}

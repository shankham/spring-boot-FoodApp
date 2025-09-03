package com.usk.demo.repository;

import com.usk.demo.dto.CartDto;
import com.usk.demo.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart,String>{


	Optional<Cart> findByUserId(String userId);

	
	@Query("SELECT new com.usk.demo.dto.CartDto(productId, productDesc, price, quanity) FROM UserCart where userId=:userId and productId=:productId")
	List<CartDto> findByUserIdAndProductId(String string, String string2);



}

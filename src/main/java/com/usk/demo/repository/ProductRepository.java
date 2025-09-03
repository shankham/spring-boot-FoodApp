package com.usk.demo.repository;

import com.usk.demo.entity.ProductList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<ProductList,Long> {

	Optional<ProductList> findByProductId(String string);


}

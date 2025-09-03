package com.usk.demo.repository;

import com.usk.demo.entity.PurchaseHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasedHistoryRepository extends MongoRepository<PurchaseHistory,String>{

	@Query( value="SELECT * FROM PURCHASE_HISTORY WHERE USER_ID=:userId AND year(PURCHASE_DATE)=:year")
	List<PurchaseHistory> getTransactionHistory(Long userId, String year);

	List<PurchaseHistory> findAllByUserId(String userId);


}

package com.usk.demo.repository;

import com.usk.demo.entity.TransactionHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionRepository extends MongoRepository<TransactionHistory,Long>{

	@Query(value="SELECT * FROM TRANSACTION_HISTORY WHERE MAIN_ACCOUNT=:acc AND month(TRANSACTION_DATE)=:month and year(TRANSACTION_DATE)=:year")
	List<TransactionHistory> getStatement(Long acc, String month, String year);

	
}

package com.usk.demo.feignClient;

import com.usk.demo.entity.UserAccountNumber;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(value="bank-service", url="http://localhost:8080/bank/")
@FeignClient(name="http://BANK-SERVICE/bank/")
public interface FundClient {

	@PostMapping("/fundTransfer")
	public String bankFundTransfer(@RequestParam double Amount, @RequestParam Long fromAcc);

	@PostMapping("/addAccount")
	public void addAccount(@RequestBody UserAccountNumber accountNo);
}

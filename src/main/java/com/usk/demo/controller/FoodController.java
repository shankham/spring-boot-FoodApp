package com.usk.demo.controller;

import com.usk.demo.dto.*;
import com.usk.demo.entity.ProductList;
import com.usk.demo.entity.PurchaseHistory;
import com.usk.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
public class FoodController {
	
	@Autowired
	UserService userservice;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate1 ;
		
	@PostMapping("/registration")
	public ResponseEntity<String> addRegistration(@Valid @RequestBody UserRegistrationDTO user) {
		 	String isRegistered=userservice.addRegistration(user);

		 	 if (isRegistered.equalsIgnoreCase("User Registration completed!!!")) {
		 	        return ResponseEntity.ok("Registration successful");
		 	    } else {
		 	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed");
		 	    }

	}	
	  
	  @GetMapping("/loginUser")
	  public String loginUser(@RequestParam String email, @RequestParam String password) {
		  return userservice.loginUser(email,password);
	  }

	  @GetMapping("/getItems")
	  public ResponseEntity<List<ProductList>> getItem() {
		  return new ResponseEntity<>(userservice.searchProduct(),HttpStatus.OK);
	  }
	 
	 @PostMapping("/addItem")
		public ResponseEntity<String> addProductInProductList(@RequestBody AddItemDTO productList) {
			 String result=userservice.addProductInProductList(productList);
			 if(result.equalsIgnoreCase("Added successfully")) {
				 return new ResponseEntity<>(result,HttpStatus.OK);
			 }
			 else{
				 return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
			 }
				 
	 }
	 
	@PutMapping("/updateItem/{id}")
	public String updateItem(@PathVariable String id, @RequestParam Long itemPrice, @RequestParam String itemQty) {
		return userservice.updateItem(itemPrice,itemQty, id);
	}
	 
	 
	 @PostMapping("/addProductInProductList")
		public String addProductInProductList(@RequestBody ProductList productList) {
			 return userservice.addProductInItemList(productList);
			 
	 }
	 
	 @PostMapping("/createCart")
		public String createCart(@RequestBody CartItemDTO userCart) {
			 return userservice.createCart(userCart);
			//return "Success";
		}
 
	
	 @PostMapping("/purchaseProduct")
	 public ResponseEntity<String> purchaseProduct(@RequestBody PurchaseProductRequest purchaseRequest) {
		return new ResponseEntity<>(userservice.purchaseProduct(purchaseRequest),HttpStatus.OK);
		
	 }
	 
	  @GetMapping("/getTransactionHistory")
	  public List<PurchaseHistory> getTransactionHistory(@RequestParam Long userId, String year){ 
		  return userservice.getTransactionHistory(userId, year); 
	  }
	  
	  @GetMapping("/OrderList/{userId}")
	  public ResponseEntity<List<PurchaseResponse>> orderByUserId(@PathVariable String userId) {
		  return new ResponseEntity<>(userservice.orderByUserId(userId),HttpStatus.OK);
	  }
	  
	  @GetMapping(value = "/producer")
		public String producer() {
		  // added message to Kafka
			 DeliveryDto  deliverydto =  new DeliveryDto();
			 
			 deliverydto.setModifyDate(LocalDateTime.now());
			 deliverydto.setItemId("1");
			 deliverydto.setItemDesc("Samosa");
			 deliverydto.setItemQuantity("10");
			 deliverydto.setItemPrice(200L);
			 
			
				
				String kafkaTopic = "delivery";
				
				
				    
				    kafkaTemplate1.send(kafkaTopic, deliverydto.toString());
	

			 
			// kafkaSender.send(deliverydto.toString());

			return "Message sent to the Kafka Topic sa_test Successfully" + deliverydto.toString();
		}
	  
}


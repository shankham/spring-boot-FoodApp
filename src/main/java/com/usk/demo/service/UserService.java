package com.usk.demo.service;

import com.usk.demo.dto.*;
import com.usk.demo.entity.ProductList;
import com.usk.demo.entity.PurchaseHistory;

import java.util.List;


public interface UserService {

	 String addRegistration(UserRegistrationDTO user);

	 
	String loginUser(String email, String password);

	List<ProductList> searchProduct();

	String addProductInProductList(AddItemDTO productList);

	String createCart(CartItemDTO userCart);


	 String purchaseProduct(PurchaseProductRequest purchaseRequest);

	List<PurchaseHistory> getTransactionHistory(Long userId, String year);

	String addProductInItemList(ProductList productList);

	String updateItem(Long itemPrice, String itemQty, String id);

	List<PurchaseResponse> orderByUserId(String userId);




	

}

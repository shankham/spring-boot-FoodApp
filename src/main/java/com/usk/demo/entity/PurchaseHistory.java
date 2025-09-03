package com.usk.demo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "purchaseHistory")
public class PurchaseHistory {

	private Long TxnId;
	
	private String userId;
	
	private Long fromAccount;

	private String productId;
//	
//	private String productDesc;
//	
	private double productPrice;
	
	private LocalDateTime purchaseDate;

	private int quantity;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId2) {
		this.userId = userId2;
	}

	public Long getTxnId() {
		return TxnId;
	}

	public void setTxnId(Long txnId) {
		TxnId = txnId;
	}

	public Long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String string) {
		this.productId = string;
	}
//
//	public String getProductDesc() {
//		return productDesc;
	//}

	/*
	 * public void setProductDesc(String productDesc) { this.productDesc =
	 * productDesc; }
	 */
	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
}

package com.usk.demo.dto;

import java.time.LocalDateTime;

public class PurchaseResponse {

	
	private String productId;
	
	private double productPrice;
	
	private LocalDateTime purchaseDate;

	private int quantity;


	public PurchaseResponse(String productId, double productPrice, LocalDateTime purchaseDate, int quantity) {
		super();
		this.productId = productId;
		this.productPrice = productPrice;
		this.purchaseDate = purchaseDate;
		this.quantity = quantity;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

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

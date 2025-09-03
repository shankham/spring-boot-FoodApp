package com.usk.demo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productList")
public class ProductList {

	
	private String productId;
	
	private String productDesc;
	
	private Long price;
	
	private String qty;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}
	
	
	
	
	
	
}

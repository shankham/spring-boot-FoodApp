package com.usk.demo.dto;

public class OrderDto {

	private Long productId;
	
	private String productDesc;
	
	private Long price;
	
	private Long quantity;

	public OrderDto(Long productId, String productDesc, Long price, Long quantity) {
		super();
		this.productId = productId;
		this.productDesc = productDesc;
		this.price = price;
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
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

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	
}

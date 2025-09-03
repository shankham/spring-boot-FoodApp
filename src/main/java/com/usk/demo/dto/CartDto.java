package com.usk.demo.dto;

public class CartDto {
	
private Long productId;
	
    private String productDesc;
	
	private Long price;
	
	private int quanity;

	public CartDto(Long productId, String productDesc, Long price, int quanity) {
		super();
		this.productId = productId;
		this.productDesc = productDesc;
		this.price = price;
		this.quanity = quanity;
	}
	public CartDto() {
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

	public int getQuanity() {
		return quanity;
	}

	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}
	
	

}

package com.usk.demo.dto;

import java.time.LocalDateTime;

public class DeliveryDto {

	private String itemId;
	
	private String itemDesc;
	
	private Long itemPrice;
	
	private String itemQuantity;
	
	private LocalDateTime ModifyDate;

	
	
	public Long getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Long itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public LocalDateTime getModifyDate() {
		return ModifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		ModifyDate = modifyDate;
	}

	

	
	public DeliveryDto(String itemId, String itemDesc, Long itemPrice, String itemQuantity,
			LocalDateTime modifyDate) {
		super();
		this.itemId = itemId;
		this.itemDesc = itemDesc;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
		ModifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "itemId=" + itemId + ", itemDesc=" + itemDesc + ", itemPrice=" + itemPrice
				+ ", itemQuantity=" + itemQuantity + ", ModifyDate=" + ModifyDate;
	}

	public DeliveryDto() {
		
	}
	
}

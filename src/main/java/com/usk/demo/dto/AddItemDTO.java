package com.usk.demo.dto;


import com.usk.demo.entity.ProductList;

public class AddItemDTO {

	private String email;

	private String password;

	private ProductList Item ;
	
	public ProductList getItem() {
		return Item;
	}

	public void setItem(ProductList Item) {
		this.Item = Item;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

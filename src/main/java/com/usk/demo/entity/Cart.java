package com.usk.demo.entity;

import com.usk.demo.entity.CartItem;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "userCart")
public class Cart {


    private String id;
    private String userId;
    private List<CartItem> items;

    private double cartTotal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(double cartTotal) {
        this.cartTotal = cartTotal;
    }

}

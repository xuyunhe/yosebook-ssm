package com.tyust.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tyust.entity.CartItem;

public interface CartItemDao {
	
	public List<CartItem> loadCartItems(String... cartItemIds) throws SQLException;
	
	public CartItem findByCartItemId(String cartItemId) throws SQLException;
	
	public void bacthDelete(String... cartItemIds) throws SQLException;
	
	public CartItem findByUidAndBid(Map<String,Object> map) throws SQLException;
	
	public void updateQuantity(Map<String,Object> map) throws SQLException;
	
	public void addCartItem(Map<String,Object> map) throws SQLException;
	
	public List<CartItem> findByUser(String uid) throws SQLException;
}

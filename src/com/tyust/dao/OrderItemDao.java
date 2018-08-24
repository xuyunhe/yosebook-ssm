package com.tyust.dao;

import java.util.List;
import java.util.Map;

import com.tyust.entity.OrderItem;

public interface OrderItemDao {

	public List<OrderItem> findByOid(String oid);
	// 1根据订单的id对订单项进行查询

	public OrderItem load(String orderItemId);
	// 2根据订单项的id对订单项进行查询

	public void addOrderItem(List<Map<String, Object>> mapList);
	// 3添加订单项但数据库；
}

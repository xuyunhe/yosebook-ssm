package com.tyust.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tyust.entity.Order;

public interface OrderDao {

	public int findStatus(String oid) throws SQLException;
	// 1根据订单的id对订单的状态进行查询年

	public void updateStatus(Map<String, Object> map) throws SQLException;

	// 2对订单的状态进行更新
	public Order load(String oid) throws SQLException;
	// 3根据订单的id对订单进行查询，返回pojo类型

	public void add(Map<String, Object> map) throws SQLException;
	// 4添加订单到数据库

	public List<Order> findByStatus(Map<String, Object> map) throws SQLException;

	// 5根据订单的状态对订单进行查询
	public List<Order> findByUser(Map<String, Object> map) throws SQLException;

	// 6根据用户对订单进行查询
	public List<Order> findAll(Map<String, Object> map) throws SQLException;

	// 7查询所有的订单
	public Integer findOrderTr(Map<String, Object> map) throws SQLException;
	// 8查询所有的订单返回integre
}

package com.tyust.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tyust.dao.OrderDao;
import com.tyust.dao.OrderItemDao;
import com.tyust.entity.Order;
import com.tyust.entity.OrderItem;
import com.tyust.pager.PageBean;
import com.tyust.pager.PageConstant;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemDao orderItemDao;
	// 1订单项进行注入

	int ps = PageConstant.ORDER_PAGE_SIZE;
	// 2在业务层为ps赋值每一页显示8个订单

	public void updateStatus(String oid, int status) {
		// 3传入订单的id和状态，存入map中进行处理
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("oid", oid);
			map.put("status", status);
			orderDao.updateStatus(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int findStatus(String oid) {
		// 4传入订单的id，根据订单的id对订单的状态进行查询，付款，已经发货.......
		try {
			return orderDao.findStatus(oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 开启事务
	@Transactional
	public Order load(String oid) {
		// 5根据订单的id对订单进行查询返回ojo类型；
		try {
			Order order = orderDao.load(oid);
			return order;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 事务
	@Transactional
	public void createOrder(Order order) {
		// 6创建订单，插入订单到数据库；
		try {
			Map<String, Object> map = toMap(order);
			// 复杂的方法进行解开；
			orderDao.add(map);
			List<OrderItem> orderItemList = order.getOrderItemList();
			List<Map<String, Object>> mapList = toMapList(orderItemList);
			orderItemDao.addOrderItem(mapList);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	// 事务
	@Transactional
	public PageBean<Order> myOrders(String uid, int pc) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uid", uid);
			map.put("ps", ps);
			map.put("start", getStart(pc));
			List<Order> orderList = orderDao.findByUser(map);
			PageBean<Order> pb = toPageBean(orderList, pc, map);
			return pb;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查询所有订单
	 * 
	 * @param pc
	 * @return
	 */
	// 事务
	@Transactional
	public PageBean<Order> findAll(int pc) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ps", ps);
			map.put("start", getStart(pc));
			List<Order> orderList = orderDao.findAll(map);
			PageBean<Order> pb = toPageBean(orderList, pc, map);
			return pb;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 事务
	@Transactional
	public PageBean<Order> findByStatus(int status, int pc) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", status);
			List<Order> orderList = orderDao.findByStatus(map);
			PageBean<Order> pb = toPageBean(orderList, pc, map);
			return pb;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private PageBean<Order> toPageBean(List<Order> orderList, int pc, Map<String, Object> map) {
		Integer tr = 0;
		// 为分页做准备，初始化数量为0
		try {
			tr = orderDao.findOrderTr(map);
			// 查询所有的订单数量
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPc(pc);// 当前的页码
		pageBean.setPs(ps);// 每一页的数量
		pageBean.setTr(tr);// 总的记录数据
		pageBean.setBeanList(orderList);// ???
		return pageBean;
	}

	private Map<String, Object> toMap(Order order) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建map对象
		map.put("oid", order.getOid());
		map.put("ordertime", order.getOrdertime());
		map.put("total", order.getTotal());
		map.put("status", order.getStatus());
		map.put("address", order.getAddress());
		map.put("uid", order.getOwner().getUid());
		return map;
	}

	private List<Map<String, Object>> toMapList(List<OrderItem> orderItemList) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (OrderItem orderItem : orderItemList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderItemId", orderItem.getOrderItemId());
			map.put("quantity", orderItem.getQuantity());
			map.put("subtotal", orderItem.getSubtotal());
			map.put("bid", orderItem.getBook().getBid());
			map.put("bname", orderItem.getBook().getBname());
			map.put("currPrice", orderItem.getBook().getCurrPrice());
			map.put("image_b", orderItem.getBook().getImage_b());
			map.put("oid", orderItem.getOrder().getOid());
			mapList.add(map);
		}
		return mapList;
	}

	private int getStart(int pc) {
		return (pc - 1) * ps;
	}
}

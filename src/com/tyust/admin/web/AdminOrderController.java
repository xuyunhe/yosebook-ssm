package com.tyust.admin.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tyust.entity.Order;
import com.tyust.pager.PageBean;
import com.tyust.service.OrderService;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController{

	@Autowired
	private OrderService orderService;
	
	private int getPc(HttpServletRequest request){
		int pc = 1;
		String param = request.getParameter("pc");
		if(param!=null && !param.trim().isEmpty()){
			try{
				pc = Integer.parseInt(param);
			}catch(RuntimeException e){ }
		}
		return pc;
	}
	
	private String getUrl(HttpServletRequest request){
		String url = request.getRequestURI()+"?"+request.getQueryString();
		int index = url.lastIndexOf("&pc=");
		if(index!=-1){
			url = url.substring(0, index);
		}
		return url;
	}

	@RequestMapping("/findAll.do")
	public String findAll(HttpServletRequest request){
		int pc = getPc(request);
		String url = getUrl(request);
		//从当前session中获取
		PageBean<Order> pb = orderService.findAll(pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		
		return "/adminjsps/admin/order/list";
	}
	
	@RequestMapping("/findByStatus.do")
	public String findByStatus(HttpServletRequest request){
		int pc = getPc(request);
		String url = getUrl(request);
		int status = Integer.parseInt(request.getParameter("status"));
		//从当前session中获取
		PageBean<Order> pb = orderService.findByStatus(status,pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		
		return "/adminjsps/admin/order/list";
	}
	
	@RequestMapping("/load.do")
	public String load(HttpServletRequest request){
		String oid = request.getParameter("oid");
		Order order = orderService.load(oid);
		request.setAttribute("order", order);
		String btn = request.getParameter("btn");
		request.setAttribute("btn", btn);
		return "/adminjsps/admin/order/desc";
	}
	
	@RequestMapping("/cancel.do")
	public String cancel(HttpServletRequest request){
		String oid = request.getParameter("oid");
		int status = orderService.findStatus(oid);
		if(status!=1){
			request.setAttribute("code", "error");
			request.setAttribute("msg", "状态不对，不能取消！");
			return "/adminjsps/msg";
		}
		orderService.updateStatus(oid, 5);//设置状态为取消
		request.setAttribute("code", "success");
		request.setAttribute("msg", "订单已取消成功！");
		return "/adminjsps/msg";
	}
	/**
	 * 发货
	 * @param request
	 */
	@RequestMapping("/deliver.do")
	public String deliver(HttpServletRequest request){
		String oid = request.getParameter("oid");
		int status = orderService.findStatus(oid);
		if(status!=2){
			request.setAttribute("code", "error");
			request.setAttribute("msg", "状态不对，不能发货！");
			return "/adminjsps/msg";
		}
		orderService.updateStatus(oid, 3);//设置状态为取消
		request.setAttribute("code", "success");
		request.setAttribute("msg", "订单已发货！");
		return "/adminjsps/msg";
	}

}

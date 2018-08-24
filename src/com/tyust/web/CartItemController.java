package com.tyust.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyust.entity.Book;
import com.tyust.entity.CartItem;
import com.tyust.entity.User;
import com.tyust.service.CartItemService;

@Controller
@RequestMapping("cartItem")
public class CartItemController{

	@Autowired
	private CartItemService cartItemService;
	
	@RequestMapping("/loadCartItems.do")
	public String loadCartItems(HttpServletRequest request,ModelMap map,String total){
		String cartItemIds = request.getParameter("cartItemIds");
		double doublTotal = Double.parseDouble(total);
		List<CartItem> cartItems = cartItemService.loadCartItems(cartItemIds);
		map.addAttribute("cartItems", cartItems);
		map.addAttribute("total", doublTotal);
		map.addAttribute("cartItemIds", cartItemIds);
		
		return "/jsps/cart/showitem";
	}
	
	@RequestMapping("/updateQuantity.do")
	@ResponseBody
	public Map<String,Object> updateQuantity(ModelMap map,
			String cartItemId,int quantity){
		CartItem cartItem = cartItemService.updateQuantity(cartItemId, quantity);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("quantity", cartItem.getQuantity());
		jsonMap.put("subtotal", cartItem.getSubtotal());
		return jsonMap;
	}
	
	@RequestMapping("/batchDelete.do")
	public String batchDelete(ModelMap map,HttpServletRequest request){
		String cartItemIds = request.getParameter("cartItemIds");
		cartItemService.batchDelete(cartItemIds);
		return myCart(map,request);
	}
	
	@RequestMapping("/add.do")
	public String add(ModelMap map,HttpServletRequest request,
			CartItem cartItem,Book book){
		User user = (User) request.getSession().getAttribute("sessionUser");
		cartItem.setBook(book);
		cartItem.setUser(user);
		//调用service，完成添加
		cartItemService.add(cartItem);
		return myCart(map,request);
	}
	
	@RequestMapping("/myCart.do")
	public String myCart(ModelMap map,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("sessionUser");
		String uid = user.getUid();
		List<CartItem> cartItemList = cartItemService.myCart(uid);
		map.addAttribute("cartItemList", cartItemList);
		return "/jsps/cart/list";
	}

}

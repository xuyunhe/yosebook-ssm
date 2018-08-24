package com.tyust.admin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tyust.admin.entity.Admin;
import com.tyust.admin.service.AdminService;
@Controller
@RequestMapping("/admin/admin")
public class AdminController{
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/quit.do")
	public String quit(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:/adminjsps/login.jsp";
	}
	
	@RequestMapping("/login.do")
	public String login(ModelMap map,String adminname,String adminpwd,HttpSession session){
		if(adminname==null || adminname.trim().isEmpty()){
			map.addAttribute("msg", "管理员账户不能为空！");
			return "/adminjsps/login";
		}else if(adminpwd==null || adminpwd.trim().isEmpty()){
			map.addAttribute("msg", "密码不能为空！");
			map.addAttribute("adminname", adminname);
			return "/adminjsps/login";
		}
		Admin admin = adminService.findByAdminnameAndAdminpwd(adminname, adminpwd);
		if(admin==null){
			map.addAttribute("msg", "管理员账户或密码错误");
			map.addAttribute("admin", admin);
			return "/adminjsps/login";
		}else{
			session.setAttribute("sessionAdmin", admin);
			return "/adminjsps/admin/index";
		}
	}

}

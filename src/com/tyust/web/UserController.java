package com.tyust.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyust.entity.User;
import com.tyust.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/logout")
	public String logoutByUser(HttpServletRequest request) {
		request.getSession().removeAttribute("sessionUser");
		// 用户推出，把sesson中的value清除；
		request.getSession().invalidate();
		// 同样可以销毁session,就是推退出
		return "redirect:/jsps/user/login.do";

	}

	@RequestMapping("/quit.do")
	public String quit(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/jsps/user/login.jsp";
	}

	@RequestMapping("/updatePassword.do")
	// ------------修改密码方法已经更换；
	public String updatePassword(User formUser, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("sessionUser");
		if (user == null) {
			request.setAttribute("msg", "您还未登录！");
			return "/jsps/user/login";
		}
		try {
			userService.updatePasswordOfUser(user.getUid(), formUser.getLoginpass(), formUser.getNewpass());
			// userService.updatePassword(user.getUid(),
			// formUser.getLoginpass(), formUser.getNewpass());
			request.setAttribute("msg", "修改密码成功！");
			request.setAttribute("code", "success");
			return "/jsps/msg";
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", formUser);
			return "/jsps/user/pwd";
		}
	}

	@RequestMapping("/login.do")
	// ------------登录方法已经更换
	public String login(User formUser, HttpSession session, ModelMap map, HttpServletResponse response)
			throws UnsupportedEncodingException {
		Map<String, String> errors = validateLogin(formUser, session);
		if (errors.size() > 0) {
			map.addAttribute("user", formUser);
			map.addAttribute("errors", errors);
			return "/jsps/user/login";
		}
		User user = userService.loginByUser(formUser);
		if (user == null) {
			map.addAttribute("msg", "用户名或密码错误！");
			map.addAttribute("user", formUser);
			return "/jsps/user/login";
		} else {
			if (!user.isStatus()) {
				map.addAttribute("msg", "您还未激活，不能登录！");
				map.addAttribute("user", formUser);
				return "/jsps/user/login";
			} else {
				session.setAttribute("sessionUser", user);
				String loginname = user.getLoginname();
				loginname = URLEncoder.encode(loginname, "utf-8");
				Cookie cookie = new Cookie("loginname", loginname);
				cookie.setMaxAge(60 * 60 * 24 * 10);
				response.addCookie(cookie);
				return "/index";
			}
		}
	}

	public Map<String, String> validateLogin(User formUser, HttpSession session) {

		Map<String, String> errors = new HashMap<String, String>();
		String loginname = formUser.getLoginname();
		if (loginname == null || loginname.trim().isEmpty()) {
			errors.put("loginname", "用户名不能为空！");
		} else if (loginname.length() <= 3 || loginname.length() > 20) {
			errors.put("loginname", "用户名必须是4~20个字符！");
		}

		String loginpass = formUser.getLoginpass();
		if (loginpass == null || loginpass.trim().isEmpty()) {
			errors.put("loginpass", "密码不能为空！");
		} else if (loginpass.length() <= 3 || loginpass.length() > 20) {
			errors.put("loginpass", "密码必须是4~20个字符！");
		}

		String verifyCode = formUser.getVerifyCode();
		String vCode = (String) session.getAttribute("vCode");
		if (verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空！");
		} else if (!verifyCode.equalsIgnoreCase(vCode)) {
			errors.put("verifyCode", "验证码错误！");
		}
		return errors;
	}

	@RequestMapping("/regist.do")
	public String regist(User formUser, HttpSession session, ModelMap map) throws Exception {
		Map<String, String> errors = validateRegist(formUser, session);
		if (errors.size() > 0) {
			map.addAttribute("form", formUser);
			map.addAttribute("errors", errors);
			return "/jsps/user/regist";
		}
		 userService.regist(formUser);
		//userService.registerByUser(formUser);
		//不通过................
		
		// 这是自己的注册方法；---------------------------------

		map.addAttribute("code", "success");
		map.addAttribute("msg", "注册成功，请马上到邮箱激活！");
		return "/jsps/msg";
	}

	public Map<String, String> validateRegist(User formUser, HttpSession session) {

		Map<String, String> errors = new HashMap<String, String>();
		String loginname = formUser.getLoginname();
		if (loginname == null || loginname.trim().isEmpty()) {
			errors.put("loginname", "用户名不能为空！");
		} else if (loginname.length() <= 3 || loginname.length() > 20) {
			errors.put("loginname", "用户名必须是4~20个字符！");
		} else if (userService.ajaxValidateLoginname(loginname)) {
			errors.put("loginname", "该用户名已被注册！");
		}

		String loginpass = formUser.getLoginpass();
		if (loginpass == null || loginpass.trim().isEmpty()) {
			errors.put("loginpass", "密码不能为空！");
		} else if (loginpass.length() <= 3 || loginpass.length() > 20) {
			errors.put("loginpass", "密码必须是4~20个字符！");
		}

		String reloginpass = formUser.getReloginpass();
		if (reloginpass == null || reloginpass.trim().isEmpty()) {
			errors.put("reloginpass", "确认密码不能为空！");
		} else if (!reloginpass.equals(loginpass)) {
			errors.put("reloginpass", "两次密码输入不相同！");
		}

		String email = formUser.getEmail();
		if (email == null || email.trim().isEmpty()) {
			errors.put("email", "Email不能为空！");
		} else if (!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")) {
			errors.put("email", "Email格式错误！");
		} else if (userService.ajaxValidateEmail(email)) {
			errors.put("email", "该Email已被注册！");
		}

		String verifyCode = formUser.getVerifyCode();
		String vCode = (String) session.getAttribute("vCode");
		if (verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空！");
		} else if (!verifyCode.equalsIgnoreCase(vCode)) {
			errors.put("verifyCode", "验证码错误！");
		}
		return errors;
	}

	@RequestMapping("/activation.do")
	public String activation(ModelMap map, String activationCode) {
		try {
			userService.activation(activationCode);

			map.addAttribute("code", "success");
			map.addAttribute("msg", "恭喜您，激活成功，马上登录吧！");
		} catch (Exception e) {
			map.addAttribute("code", "error");
			map.addAttribute("msg", e.getMessage());
		}
		return "/jsps/msg";
	}

	@RequestMapping("/ajaxValidateLoginname.do")
	@ResponseBody
	public boolean ajaxValidateLoginname(String loginname) {
		boolean b = userService.ajaxValidateLoginname(loginname);
		return b;
	}

	@RequestMapping("/ajaxValidateEmail.do")
	@ResponseBody
	public boolean ajaxValidateEmail(String email) {
		boolean b = userService.ajaxValidateEmail(email);
		return b;
	}

	@RequestMapping("/ajaxValidateVerifyCode.do")
	@ResponseBody
	public boolean ajaxValidateVerifyCode(String verifyCode, HttpSession session) {
		String vCode = (String) session.getAttribute("vCode");
		boolean b = verifyCode.equalsIgnoreCase(vCode);
		return b;
	}
}

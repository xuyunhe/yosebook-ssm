package com.tyust.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyust.dao.UserDao;
import com.tyust.entity.User;
import com.tyust.util.commons.CommonUtil;
import com.tyust.util.mail.Mail;
import com.tyust.util.mail.MailUtil;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	// <!-- 2用户更新密码 -->
	@Override
	public void updatePasswordOfUser(String uid, String oldPass, String newPass) throws Exception {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("uid", uid);
			map.put("oldPass", oldPass);
			map.put("newPass", newPass);
			boolean findByUidAndLoginpass = userDao.findByUidAndLoginpass(map);
			if (!findByUidAndLoginpass) {
				throw new UserException("你的密码或者用户名有误，请核对.............");
				// 原始密码有误；
			}
			// 原始的密码和用户名是正确的;
			userDao.updateLoginpass(map);
			// 更新密码；
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		// TODO Auto-generated method stub

	}

	public void updatePassword(String uid, String oldPass, String newPass) throws Exception {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("uid", uid);
			map.put("oldPass", oldPass);
			map.put("newPass", newPass);
			boolean bool = userDao.findByUidAndLoginpass(map);
			// 根据原始密码对数据库进行查找，找到后返回true，下面的if语句不会执行，对数据库进行更新
			// 如果返回为false说明没有找到if语句为true进行执行跑出原始密码错误的异常；
			if (!bool) {
				throw new UserException("原密码错误");
			}
			userDao.updateLoginpass(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// <!-- 3根据用户姓名和密码进行登录 -->
	@Override
	public User loginByUser(User user) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("loginname", user.getLoginname());
			map.put("loginpass", user.getLoginpass());
			// TODO Auto-generated method stub
			return userDao.findByLoginnameAndLoginpass(map);
		} catch (SQLException e) {
			throw new RuntimeException();
			// TODO Auto-generated catch block
		}
	}

	public User login(User user) {

		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("loginname", user.getLoginname());
			map.put("loginpass", user.getLoginpass());
			return userDao.findByLoginnameAndLoginpass(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// <!-- 4根据激活码进行查询用户 -->
	@Override
	public void activationUser(String activeCode) throws Exception {
		User user = null;
		try {
			user = userDao.findByCode(activeCode);
			if (user == null) {
				throw new UserException("无效的激活码，你是黑客么？？？");
			}
			if (user.isStatus()) {
				// 已经激活了
				throw new UserException("我去已经激活了，是不是吃饱了撑着？？？");
			}
			// 走到这里说明该黑没有激活；
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uid", user.getUid());
			map.put("status", "true");
			userDao.updateStatus(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}

		// TODO Auto-generated method stub

	}

	public void activation(String code) throws Exception {
		User user;
		try {
			user = userDao.findByCode(code);
			// 1查找激活码。调用查找方法
			if (user == null)
				// 返回为null证明没有找到对应的用户；
				throw new UserException("无效的激活码！");
			if (user.isStatus())
				// user.isstate默认为false;
				throw new UserException("您已激活过该账号，不能二次激活！");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uid", user.getUid());
			map.put("status", true);
			// 数据库没有boolean类型,表示布尔类型就用tinyint,只有0和1对应false和true;
			userDao.updateStatus(map);
			// 2进行激活，把数据库中的state状态修改为1
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	// <!-- 6根据ajax区验证注册的用户名时候存在，数据库中要求用户名唯一 -->

	public boolean ajaxValidateLoginname(String loginname) {
		try {
			return userDao.ajaxValidateLoginname(loginname);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	// <!-- 7验证用户的邮箱时候已经注册，数据库中要求唯一 -->

	public boolean ajaxValidateEmail(String email) {
		try {
			return userDao.ajaxValidateEmail(email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	// <!-- 8用户的注册，激活码是什么鬼？？？？？？ -->

	public void regist(User user) throws Exception {
		user.setUid(CommonUtil.uuid());
		user.setStatus(false);
		user.setActivationCode(CommonUtil.uuid() + CommonUtil.uuid());
		try {
			userDao.add(user);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 发邮件
		Properties prop = new Properties();
		try {
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("email_template.properties");
			prop.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String host = prop.getProperty("host");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		Session session = MailUtil.createSession(host, username, password);
		String from = prop.getProperty("from");
		String to = user.getEmail();
		String subject = prop.getProperty("subject");
		String content = MessageFormat.format(prop.getProperty("content"), user.getActivationCode());
		Mail mail = new Mail(from, to, subject, content);
		try {
			MailUtil.send(session, mail);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tyust.service.UserService#updatePasswordOfUser(java.lang.String,
	 * java.lang.String, java.lang.String) 这是实现的接口；
	 */

	@Override
	public boolean ajaxValidateLoginName(String loginName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ajaxValidateEmailIsRight(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerByUser(User user) {
		// TODO Auto-generated method stub

	}

}

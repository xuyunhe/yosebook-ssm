package com.tyust.service;

import com.tyust.entity.User;

public interface UserService {
	// 1用户更改密码；
	public void updatePasswordOfUser(String uid, String oldPass, String newPass)throws Exception;

	public void updatePassword(String uid, String oldPass, String newPass) throws Exception;

	// 2用户登录
	public User loginByUser(User user);

	public User login(User user);

	// 3用户激活
	public void activationUser(String activeCode)throws Exception;

	public void activation(String code) throws Exception;

	// 4校验用户名是否可以使用，注册时候用的
	public boolean ajaxValidateLoginName(String loginName);

	public boolean ajaxValidateLoginname(String loginname);

	// 5验证邮箱是否是合法的
	public boolean ajaxValidateEmailIsRight(String email);

	public boolean ajaxValidateEmail(String email);

	// 6用户的注册；
	public void registerByUser(User user) throws Exception;

	public void regist(User user) throws Exception;

}

package com.tyust.dao;

import java.sql.SQLException;
import java.util.Map;

import com.tyust.entity.User;

public interface UserDao {
	//<!-- 1根据用户id和登录密码进行查找 ，返回值是布尔 -->

	public boolean findByUidAndLoginpass(Map<String,String> map) throws SQLException;
	//	<!-- 2用户更新密码 -->

	public void updateLoginpass(Map<String,String> map) throws SQLException;
	//	<!-- 3根据用户姓名和密码进行登录 -->

	public User findByLoginnameAndLoginpass(Map<String,String> map) throws SQLException;
	//	<!-- 4根据激活码进行查询用户 -->

	public User findByCode(String code) throws SQLException;
	//	<!-- 5根据激活码区吧账户激活，及吧数据库中的User表中的state状态修改为1 -->

	public void updateStatus(Map<String,Object> map) throws SQLException;
	//	<!-- 6根据ajax区验证注册的用户名时候存在，数据库中要求用户名唯一 -->

	public boolean ajaxValidateLoginname(String loginname) throws SQLException;
	//	<!-- 7验证用户的邮箱时候已经注册，数据库中要求唯一 -->

	public boolean ajaxValidateEmail(String email) throws SQLException;
	//	<!-- 8用户的注册，激活码是什么鬼？？？？？？ -->

	public void add(User user) throws SQLException;
	//
}

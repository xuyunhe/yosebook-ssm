package com.tyust.admin.dao;

import java.sql.SQLException;
import java.util.Map;

import com.tyust.admin.entity.Admin;

public interface AdminDao {
//1.后台管理员的登录
	
	public Admin findByAdminnameAndAdminpwd(Map<String,Object> map) throws SQLException;
}

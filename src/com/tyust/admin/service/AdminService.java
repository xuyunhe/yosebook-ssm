package com.tyust.admin.service;

import com.tyust.admin.entity.Admin;

public interface AdminService {
	// 1根据管理员的名字和密码进行登录；
	public Admin findAdminByNameAndPassword(String adminName, String adminPassword);

	public Admin findByAdminnameAndAdminpwd(String adminname, String adminpwd);
}

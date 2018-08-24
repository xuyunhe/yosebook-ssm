package com.tyust.admin.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyust.admin.dao.AdminDao;
import com.tyust.admin.entity.Admin;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	// 1注入接口；

	public Admin findByAdminnameAndAdminpwd(String adminname, String adminpwd) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("adminname", adminname);
			map.put("adminpwd", adminpwd);
			return adminDao.findByAdminnameAndAdminpwd(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Admin findAdminByNameAndPassword(String adminName, String adminPassword) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("adminname", adminName);
			map.put("adminpwd", adminPassword);
			// 记得区分大小写
			// TODO Auto-generated method stub
			return adminDao.findByAdminnameAndAdminpwd(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
			// 抛出异常谁调用的谁处理；
		}
	}

}

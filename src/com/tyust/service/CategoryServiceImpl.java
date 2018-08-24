package com.tyust.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyust.dao.CategoryDao;
import com.tyust.entity.Category;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryDao categoryDao;
	
	public void delete(String cid){
		try {
			categoryDao.delete(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int findChildrenByParent(String pid){
		try {
			return categoryDao.findChildrenByParent(pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void edit(Category category){
		try {
			Map<String,Object> map = toMap(category);
			categoryDao.edit(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Category load(String cid){
		try {
			return categoryDao.load(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void add(Category category){
		try {
			Map<String,Object> map = toMap(category);
			categoryDao.add(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Category> findAll(){
		try {
			return categoryDao.findParent();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Category> findParents(){
		try {
			return categoryDao.findParent();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Category> findByParent(String pid){
		try {
			return categoryDao.findByParent(pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Map<String,Object> toMap(Category category){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cid", category.getCid());
		if(category.getCname()!=null)
		map.put("cname", category.getCname());
		if(category.getParent()!=null)
			if(category.getParent().getCid()!=null)
		map.put("pid", category.getParent().getCid());
		if(category.getDesc()!=null)
		map.put("desc", category.getDesc());
		return map;
	}
}

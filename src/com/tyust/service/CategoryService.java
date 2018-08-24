package com.tyust.service;

import java.util.List;

import com.tyust.entity.Category;

public interface CategoryService {

	public void delete(String cid);
	
	public int findChildrenByParent(String pid);
	
	public void edit(Category category);
	
	public Category load(String cid);
	
	public void add(Category category);
	
	public List<Category> findAll();
	
	public List<Category> findParents();
	
	public List<Category> findByParent(String pid);
}

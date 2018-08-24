package com.tyust.admin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tyust.entity.Category;
import com.tyust.service.BookService;
import com.tyust.service.CategoryService;
import com.tyust.util.commons.CommonUtil;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController{
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/findAll.do")
	public String findAll(ModelMap map){
		map.addAttribute("parents", categoryService.findAll());
		return "/adminjsps/admin/category/list";
	}
	
	@RequestMapping("/addParent.do")
	public String addParent(ModelMap map,Category parent){
		parent.setCid(CommonUtil.uuid());
		categoryService.add(parent);
		return findAll(map);
	}
	
	/**
	 * 添加二级分类第一步
	 */
	@RequestMapping("/addChildPre.do")
	public String addChildPre(ModelMap map,String pid)
					throws ServletException, IOException {
		List<Category> parents = categoryService.findParents();
		map.addAttribute("pid", pid);
		map.addAttribute("parents", parents);
		return "/adminjsps/admin/category/add2";
	}
	
	@RequestMapping("/addChild.do")
	public String addChild(ModelMap map,Category child,String pid){
		child.setCid(CommonUtil.uuid());
		Category parent = new Category();
		parent.setCid(pid);
		child.setParent(parent);
		categoryService.add(child);
		return findAll(map);
	}
	
	@RequestMapping("/editParentPre.do")
	public String editParentPre(ModelMap map,String cid){
		Category parent = categoryService.load(cid);
		map.addAttribute("parent", parent);
		return "/adminjsps/admin/category/edit";
	}
	
	@RequestMapping("/editParent.do")
	public String editParent(ModelMap map,Category category){
		categoryService.edit(category);
		return findAll(map);
	}
	
	@RequestMapping("/editChildPre.do")
	public String editChildPre(ModelMap map,String cid){
		Category child = categoryService.load(cid);
		map.addAttribute("child", child);
		map.addAttribute("parents", categoryService.findParents());
		return "/adminjsps/admin/category/edit2";
	}
	
	@RequestMapping("/editChild.do")
	public String editChild(ModelMap map,Category category,String pid){
		Category parent = new Category();
		parent.setCid(pid);
		category.setParent(parent);
		categoryService.edit(category);
		return findAll(map);
	}
	
	@RequestMapping("/deleteParent.do")
	public String deleteParent(ModelMap map,String cid){
		//获取一级分类的cid
		int number = categoryService.findChildrenByParent(cid);
		if(number > 0){
			map.addAttribute("msg", "该分类下还有子分类，不能删除！");
			return "/adminjsps/msg";
		}else{
			categoryService.delete(cid);
			return findAll(map);
		}
	}
	/**
	 * 删除二级分类
	 */
	@RequestMapping("/deleteChild.do")
	public String deleteChild(ModelMap map,String cid){
		int number = bookService.findBookCountByCategory(cid);
		if(number > 0){
			map.addAttribute("msg", "该分类下还存在图书，不能删除！");
			return "/adminjsps/msg";
		}else{
			categoryService.delete(cid);
			return findAll(map);
		}
	}

}

package com.tyust.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tyust.entity.Category;
import com.tyust.service.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("ll")
	public String fandAllCategory(ModelMap map) {
		List<Category> findAll = categoryService.findAll();
		map.addAttribute("pa", findAll);
		return "/jsps/left";
	}

	@RequestMapping("/findAll.do")
	public String findAll(ModelMap map) {
		List<Category> parents = categoryService.findAll();
		map.addAttribute("parents", parents);
		return "/jsps/left";
	}
}

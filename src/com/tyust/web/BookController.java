package com.tyust.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tyust.entity.Book;
import com.tyust.pager.PageBean;
import com.tyust.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/load.do")
	public String load(String bid,ModelMap map){
		Book book = bookService.load(bid);
		map.addAttribute("book", book);
		return "/jsps/book/desc";
	}
	
	private int getPc(HttpServletRequest request){
		int pc = 1;
		String param = request.getParameter("pc");
		if(param!=null && !param.trim().isEmpty()){
			try{
				pc = Integer.parseInt(param);
			}catch(RuntimeException e){ }
		}
		return pc;
	}
	
	private String getUrl(HttpServletRequest request){
		String url = request.getRequestURI()+"?"+request.getQueryString();
		int index = url.lastIndexOf("&pc=");
		if(index!=-1){
			url = url.substring(0, index);
		}
		return url;
	}
	
	@RequestMapping("/findByCategory.do")
	public String findByCategory(HttpServletRequest request){
		int pc = getPc(request);
		String url = getUrl(request);
		String cid = request.getParameter("cid");
		PageBean<Book> pb = bookService.findByCategory(cid, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "/jsps/book/list";
	}
	
	@RequestMapping("/findByAuthor.do")
	public String findByAuthor(HttpServletRequest request){
		int pc = getPc(request);
		String url = getUrl(request);
		String author = request.getParameter("author");
		PageBean<Book> pb = bookService.findByAuthor(author, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "/jsps/book/list";
	}
	
	@RequestMapping("/findByPress.do")
	public String findByPress(HttpServletRequest request){
		int pc = getPc(request);
		String url = getUrl(request);
		String press = request.getParameter("press");
		PageBean<Book> pb = bookService.findByPress(press, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "/jsps/book/list";
	}
	
	@RequestMapping("/findByBname.do")
	public String findByBname(HttpServletRequest request){
		int pc = getPc(request);
		String url = getUrl(request);
		String bname = request.getParameter("bname");
		PageBean<Book> pb = bookService.findByBname(bname, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "/jsps/book/list";
	}
	
	@RequestMapping("/findByConbination.do")
	public String findByConbination(HttpServletRequest request,Book criteria){
		int pc = getPc(request);
		String url = getUrl(request);
		
		PageBean<Book> pb = bookService.findByConbination(criteria, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "/jsps/book/list";
	}
}

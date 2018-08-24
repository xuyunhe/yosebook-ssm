package com.tyust.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyust.dao.BookDao;
import com.tyust.entity.Book;
import com.tyust.pager.PageBean;
import com.tyust.pager.PageConstant;
@Service("bookService")
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookDao bookDao;
	int ps = PageConstant.BOOK_PAGE_SIZE;
	//每一页显示的数量pagesize
	
	private int getStart(int pc){
		return (pc-1)*ps;
	}
	
	private PageBean<Book> toPageBean(List<Book> bookList,int pc,Map<String,Object> map){
		Integer tr=0;
		//初始化tr=0;
		try {
			tr = bookDao.findBookTr(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		PageBean<Book> pageBean = new PageBean<Book>();
		pageBean.setPc(pc);
		pageBean.setPs(ps);
		pageBean.setTr(tr);
		pageBean.setBeanList(bookList);
		return pageBean;
	}
	
	public int findBookCountByCategory(String cid){
		//查询某一分类下的图书总数；
		try {
			return bookDao.findBookCountByCategory(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Book load(String bid){
		//查询单个书籍
		try {
			return bookDao.findByBid(bid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public PageBean<Book> findByCategory(String cid, int pc){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cid", cid);
		map.put("ps", ps);
		map.put("start", getStart(pc));
		List<Book> bookList;
		try {
			bookList = bookDao.findByCategory(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return toPageBean(bookList, pc,map);
	}
	
	public PageBean<Book> findByBname(String bname, int pc){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bname", bname);
		map.put("ps", ps);
		map.put("start", getStart(pc));
		List<Book> bookList;
		try {
			bookList = bookDao.findByBname(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return toPageBean(bookList, pc,map);
	}
	
	public PageBean<Book> findByAuthor(String author, int pc){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("author", author);
		map.put("ps", ps);
		map.put("start", getStart(pc));
		List<Book> bookList;
		try {
			bookList = bookDao.findByAuthor(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return toPageBean(bookList, pc,map);
	}
	
	public PageBean<Book> findByPress(String press, int pc){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("press", press);
		map.put("ps", ps);
		map.put("start", getStart(pc));
		List<Book> bookList;
		try {
			bookList = bookDao.findByPress(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return toPageBean(bookList, pc,map);
	}
	
	public PageBean<Book> findByConbination(Book criteria, int pc){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bname", criteria.getBname());
		map.put("author", criteria.getAuthor());
		map.put("press", criteria.getPress());
		map.put("ps", ps);
		map.put("start", getStart(pc));
		List<Book> bookList;
		try {
			bookList = bookDao.findByCombination(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return toPageBean(bookList, pc,map);
	}
	
	public void addBook(Book book){
		try { 
			Map<String,Object> map = toMap(book);
			bookDao.add(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void edit(Book book){
		try {
			Map<String,Object> map = toMap(book);
			bookDao.edit(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String bid){
		try {
			bookDao.delete(bid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Map<String,Object> toMap(Book book){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bid", book.getBid());
		map.put("bname", book.getBname());
		map.put("author", book.getAuthor());
		map.put("price", book.getPrice());
		map.put("currPrice", book.getCurrPrice());
		map.put("discount", book.getDiscount());
		map.put("press", book.getPress());
		map.put("publishtime", book.getPublishtime());
		map.put("edition", book.getEdition());
		map.put("pageNum", book.getPageNum());
		map.put("wordNum", book.getWordNum());
		map.put("printtime", book.getPrinttime());
		map.put("booksize", book.getBooksize());
		map.put("paper", book.getPaper());
		map.put("cid", book.getCategory().getCid());
		map.put("image_w", book.getImage_w());
		map.put("image_b", book.getImage_b());
		return map;
	}

	@Override
	public int findBookTotalCountByCategoryId(String cid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageBean<Book> findBookByCategoryId(String cid, int pc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<Book> findBookByName(String bname, int pc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<Book> findBookByAuthor(String author, int pc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<Book> findBookByPress(String press, int pc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addOneBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editOneBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOneBookById(String bid) {
		// TODO Auto-generated method stub
		
	}
}

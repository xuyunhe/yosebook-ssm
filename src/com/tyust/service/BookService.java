package com.tyust.service;

import com.tyust.entity.Book;
import com.tyust.pager.PageBean;

public interface BookService {

	public int findBookCountByCategory(String cid);

	// 1根据分类id查询某一分类下的书籍数量
	public int findBookTotalCountByCategoryId(String cid);

	public Book load(String bid);
	// 2加载单个的图书，根据图书id

	public PageBean<Book> findByCategory(String cid, int pc);

	// 3按照图书分类进行查询，某一分类下的所有图书信息，分页；
	public PageBean<Book> findBookByCategoryId(String cid, int pc);

	public PageBean<Book> findByBname(String bname, int pc);

	// 4按照图书的名字进行查询；
	public PageBean<Book> findBookByName(String bname, int pc);

	public PageBean<Book> findByAuthor(String author, int pc);

	// 5按照图书的author进行查询
	public PageBean<Book> findBookByAuthor(String author, int pc);

	public PageBean<Book> findByPress(String press, int pc);

	// 6按照出版社进行查找
	public PageBean<Book> findBookByPress(String press, int pc);

	public PageBean<Book> findByConbination(Book criteria, int pc);

	public void addBook(Book book);

	// 7添加书籍；
	public void addOneBook(Book book);

	public void edit(Book book);

	// 8修改编辑书籍；
	public void editOneBook(Book book);

	public void delete(String bid);

	// 9根据实际id对书籍进行删除；
	public void deleteOneBookById(String bid);

}

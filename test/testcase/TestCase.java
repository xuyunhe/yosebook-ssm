package testcase;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tyust.dao.BookDao;
import com.tyust.dao.CartItemDao;
import com.tyust.dao.CategoryDao;
import com.tyust.dao.OrderDao;
import com.tyust.dao.OrderItemDao;
import com.tyust.entity.CartItem;
import com.tyust.entity.Category;
import com.tyust.entity.Order;
import com.tyust.entity.OrderItem;
import com.tyust.service.BookService;
import com.tyust.service.CartItemService;
import com.tyust.service.CategoryService;
import com.tyust.service.OrderService;

public class TestCase {
	private ApplicationContext ac;
	CategoryDao categoryDao;
	CategoryService categoryService;
	BookService bookService;
	BookDao bookDao;
	CartItemService cartItemService;
	CartItemDao cartItemDao;
	OrderDao orderDao;
	OrderService orderService;
	OrderItemDao orderItemDao;
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext(
				"spring-mybatis.xml",
				"spring-service.xml");
		categoryDao = ac.getBean("categoryDao",CategoryDao.class);
		categoryService = ac.getBean("categoryService",CategoryService.class);
		bookService = ac.getBean("bookService",BookService.class);
		bookDao = ac.getBean("bookDao",BookDao.class);
		cartItemService = ac.getBean("cartItemService",CartItemService.class);
		cartItemDao = ac.getBean("cartItemDao",CartItemDao.class);
		orderDao = ac.getBean("orderDao",OrderDao.class);
		orderService = ac.getBean("orderService",OrderService.class);
		orderItemDao = ac.getBean("orderItemDao",OrderItemDao.class);
	}

	@Test
	public void testMybatis() {
		Object obj = ac.getBean("dataSource");
		System.out.println(obj);
	}
	@Test
	public void testCategory() throws SQLException{
		/*List<Map<String,Object>> categoryList = categoryDao.findAll();
		for(Map<String, Object> category : categoryList){
			System.out.println(category);
		}*/
		
		/*int num = categoryDao.findChildrenByParent("1");
		System.out.println(num);*/
		
		/*Category category = categoryDao.load("1");
		System.out.println(category);
		//System.out.println(category.getChildren());
		 * 
*/		/*List<Map<String,String>> categoryList = categoryDao.findByParent("2");
		for(Map<String, String> category : categoryList){
			System.out.println(category);
		}*/
		
		/*Map<String,String> map = categoryDao.load("1");
		System.out.println(map);*/
		/*System.out.println(category.getParent());*/
		
	}
	@Test
	public void testCategoryService(){
		List<Category> categoryList = categoryService.findAll();
		for(Category cate : categoryList){
			System.out.println(cate);
		}
		
		/*List<Category> categoryList = categoryService.findParents();
		for(Category cate : categoryList){
			System.out.println(cate);
		}*/
		
		/*List<Category> categoryList = categoryService.findByParent("1");
		for(Category cate : categoryList){
			System.out.println(cate);
		}*/
	}
	//5F79D0D246AD4216AC04E9C5FAB3199E
	@Test
	public void testBook() throws SQLException{
		/*PageBean<Book> pb = bookService.findByBname("web", 1);
		System.out.println(pb.getBeanList());*/
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cid", "5F79D0D246AD4216AC04E9C5FAB3199E");
		map.put("ps", 12);
		map.put("start", 0);
		//List<Book> bookList = bookDao.findByBname(map);
		Integer n = bookDao.findBookTr(map);
		System.out.println(n);
		/*int n = bookDao.findBookTr(map);
		System.out.println(n);*/
	}
	
	@Test
	public void testCartItem() throws SQLException{
		//List<CartItem> cartItemList = cartItemService.myCart("531D8A16D524478D86F8A115FE95D93F");
		List<CartItem> cartItemList = cartItemDao.findByUser("531D8A16D524478D86F8A115FE95D93F");
		System.out.println(cartItemList);
	}
	//FAC08EE0DF2C4487A6FA2B11B343307E
	@Test
	public void testOrder() throws SQLException{
		Order order = orderDao.load("FAC08EE0DF2C4487A6FA2B11B343307E");
		System.out.println(order.getOrderItemList());
		/*OrderItem orderItem = orderItemDao.load("06AD3253E7EA415B828D1299462DE812");
		System.out.println(orderItem);*/
	}

}

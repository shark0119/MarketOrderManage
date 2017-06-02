package test;

import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.shark.entity.Order;
import com.shark.entity.Pager;
import com.shark.entity.Product;
import com.shark.service.OrderService;
import com.shark.service.ProductService;
import com.shark.util.CommonUtil;

public class OrderServiceTest {
	@Test
	public void testGetOrderList (){
		OrderService os = new OrderService();
		Pager page = new Pager(1,5,0,0);
		for (Order order: os.getOrderList(page)){
			System.out.println(order);
		}
		System.out.println(page);	
	}
	@Test
	public void testGetOrderListWithCon(){
		OrderService os = new OrderService();
		Pager page = new Pager(1,5,0,0);
		Order condition = new Order();
		condition.setIspay(1);
		for (Order order: os.getOrderList(page,condition, 1, 1)){
			System.out.println(order);
		}
		System.out.println(page);
	}
	@Test
	public void testExit (){
		OrderService os = new OrderService();
		System.out.println(os.existNotDone(2));
	}
	
	@Test
	public void transJson (){
		ProductService ps = CommonUtil.getProductService();
		List <Product> iList = ps.getProductList();
		System.out.println(iList);
		String str  = JSON.toJSON(iList).toString();
		System.out.println( str );
	}
}

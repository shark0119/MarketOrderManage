package test;

import org.junit.Test;

import com.shark.entity.Order;
import com.shark.entity.Pager;
import com.shark.service.OrderService;

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
}

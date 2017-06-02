package test;

import org.junit.Test;

import com.shark.service.ProductService;

public class ProductServiceTest {
	private ProductService ps =new ProductService();
	@Test
	public void testGetPro (){
		System.out.println(ps.getProductByC_Pid(2));
	}
}

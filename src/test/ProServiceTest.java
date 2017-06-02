package test;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.shark.entity.Pager;
import com.shark.entity.Provider;
import com.shark.service.ProService;
import com.shark.util.CommonUtil;

public class ProServiceTest {
	private ProService ps = CommonUtil.getProService();
	private Provider p;
	@Test
	public void testAddPro (){
		p = ps.checkPro(38);
		System.out.println(p);
		p.setName("公司3");
		//ps.addPro(p);
	}
	@Test
	public void testCheckPr(){
		System.out.println((new Date()).getTime());
	}
	@Test
	public void testGetPList (){
		Pager page =new Pager(1, 15, 0, 0);
		for (Provider p: ps.getProList(page)){
			System.out.println(p);
		}
		System.out.println(page);
	}
	@Test 
	public void testGetPlist2 (){
		Pager page =new Pager(1, 15, 0, 0);
		Provider condition = new Provider();
		condition.setName("公司");
		List <Provider> plist = ps.getProList(page, condition);
		for (Provider p: plist ){
			System.out.println(p);
		}
		System.out.println(page);		
	}
	@Test
	public void testTotal (){
		System.out.println(ps.getTotal());
	}
	@Test
	public void testTotal2 (){
		Provider condition = new Provider();
		condition.setName("公司");
		System.out.println(ps.getTotal(condition));
	}
	@Test
	public void testUpdate (){
		p = ps.checkPro(34);
		System.out.println(p);
		p.setName("公司1");
		ps.updatePro(p);
	}
	
	@Test
	public void testRemove (){
		if (CommonUtil.getOrderService().existNotDone(1)){
			System.out.println("开始删除");
			System.out.println(CommonUtil.getProService().removePro(2));
		}
	}
	@Test
	public void testEncoding () throws UnsupportedEncodingException{
		String pname = new String("公司".getBytes("UTF-8"), "ISO-8859-1");
		pname = new String(pname.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println(pname);
	}
}

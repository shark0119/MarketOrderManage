package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.shark.entity.Pager;

public class PagerTest {
	private Pager pager;
	@Before
	public void init (){
		pager = new Pager();
	}
	@Test
	public void testSetPageIndex() {
		pager.setPageIndex(10);
		assertEquals(1,  pager.getPageIndex()) ;
		pager.setPageIndex(0);
		assertEquals(1, pager.getPageIndex());
		pager.setTotalCount(10);
		pager.setPageIndex(4);
		assertEquals(4, pager.getPageIndex());
	}
	@Test
	public void testSetPageSize() {
		assertEquals(3, pager.getPageSize());
		pager.setPageSize(10);
		assertEquals(10, pager.getPageSize());
		pager.setPageSize(0);
		assertEquals(10, pager.getPageSize());
	}
	@Test
	public void testSetTotalCount() {
		assertEquals(0, pager.getTotalCount());
		assertEquals(1, pager.getTotalPage());
		pager.setTotalCount(10);
		assertEquals(4, pager.getTotalPage());
		assertEquals(10, pager.getTotalCount());
		pager.setTotalCount(0);
		assertEquals(10, pager.getTotalCount());
	}
	@Test
	public void testTotalPage (){
		assertEquals(1, pager.getTotalPage());
		pager.setTotalCount(15);
		assertEquals(5, pager.getTotalPage());
		pager.setPageSize(4);
		assertEquals(4, pager.getTotalPage());
	}
}

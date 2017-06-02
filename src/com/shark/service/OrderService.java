package com.shark.service;

import java.util.List;

import com.shark.dao.OrderDao;
import com.shark.dao.impl.OrderDaoImpl;
import com.shark.entity.Order;
import com.shark.entity.Pager;
import com.shark.entity.Product;
import com.shark.entity.Provider;
import com.shark.sql.CommonSql;
import com.shark.util.CommonUtil;

public class OrderService {
	private OrderDao od;
	public OrderService(){
		od = new OrderDaoImpl();
	}
	/**
	 * 添加订单
	 * @return 失败返回false 成功返回true
	 */
	public boolean addOrder (Order order){
		String sql = " insert into mk_order (id, time, ispay, money, counts, order_desc, c_pid)"
				+ " values (seq_order.nextval, sysdate,? ,? ,? ,? ,?)";
		return 1 == od.updateOrder(new CommonSql(sql, order.getIspay(), order.getMoney(), order.getCount(),
				order.getDesc(), order.getC_pid()));
	}
	
	/**
	 * 修改订单
	 * @return 失败返回false 成功返回true 
	 */
	public boolean updateOrder (Order order){
		String sql = " update mk_order set ispay=?, money=?, counts=?, order_desc=?, c_pid=? where id = ? ";
		int row = od.updateOrder(new CommonSql(sql, order.getIspay(), order.getMoney(), 
						order.getCount(), order.getDesc(),order.getC_pid(), order.getId()));
		return row == 1? true: false;
	}
	/**
	 * 
	 * @param id 根据ID删除订单
	 * @return 成功返回true 失败返回false
	 */
	public boolean removeOrder (int id){
		String sql = "delete from mk_order where id = ?";
		int row = od.updateOrder(new CommonSql(sql, id));
		return row == 1? true: false;
	}
	/**
	 * 根据ID来查找订单
	 * @param id
	 * @return 失败或为查到返回null， 成功返回订单实体
	 */
	public Order getOrder (int id){
		String sql = "select * from mk_order where id = ? ";
		Order order = od.getOrder(new CommonSql(sql, id));
		order.setProductName(CommonUtil.getProService().getProviderByC_Pid(order.getC_pid()).getName());
		return order;
	}
	/**
	 * 无条件分页查询
	 * @param pager 包含了分页信息的实体
	 * @return 数据库错误返回null, 无结果或成功返回订单集合
	 */
	public List<Order> getOrderList (Pager pager){
		return getOrderList (pager, null, -1, -1);
	}
	/**
	 * 条件分页查询
	 * @param pager 包含了分页信息的实体
	 * @param condition 包含了条件的订单实体
	 * @param productId 产品ID	无此项填-1
	 * @param providerId 供应商ID 无此项填-1
	 * @return 数据库错误返回null, 无结果或成功返回订单集合
	 */
	public List<Order> getOrderList (Pager pager, Order condition, int productId, int providerId){
		pager.setTotalCount(getTotal(condition, productId, providerId));
		String sql ;
		sql ="select * from (select t.* , rownum rn from (select * from mk_order where c_pid in "
				+ "(select id from c_provide where 1=1 ";
		
		if (productId != -1){
			sql += " and product_id = "+productId;
		}
		if (providerId != -1){
			sql += " and provider_id = "+providerId;
		}
		sql +=" ) ";
		if (condition != null && condition.getIspay() != null){
			sql += " and ispay="+condition.getIspay();
		}
		sql +=" )t)t1 where rn>? and rn<=? ";
		int begin = pager.getPageSize()*(pager.getPageIndex()-1);
		int end = pager.getPageSize()* pager.getPageIndex();
		
		System.out.println("分页查询语句: "+sql + " \n\tbegin :"+ begin +" end:"+end);
		
		List <Order> oList = od.getOrderList(new CommonSql(sql, begin, end));
		for (Order order: oList){
			Product p1 = CommonUtil.getProductService().getProductByC_Pid(order.getC_pid());
			Provider p2 = CommonUtil.getProService().getProviderByC_Pid(order.getC_pid());
			if (p2 != null)
				order.setProviderName(p2.getName());
			if (p1 != null)
				order.setProductName(p1.getName());
		}
		return oList;
	}
	/**
	 * 无条件查询订单总数
	 * @return 失败返回-1，成功返回个数
	 */
	public int getTotal (){
		return getTotal(null, -1, -1);
	}
	/**
	 * 条件查询订单总数
	 * @param condition 封装了条件信息的订单实体
	 * @param productId 产品ID	无此项填-1
	 * @param providerId 供应商ID 无此项填-1
	 * @return 失败返回-1，成功返回个数
	 */
	public int getTotal (Order condition, int productId, int providerId ){
		String sql = "select count(1) from mk_order where c_pid in (select id from c_provide where 1=1";
		if (productId != -1){
			sql += " and product_id ="+productId;
		}
		if (providerId != -1){
			sql += " and provider_Id="+ providerId;
		}
		sql +=  ")";
		if (condition != null && condition.getIspay() != null && condition.getIspay() != -1)
			sql += " and ispay= "+condition.getIspay();
		System.out.println("\n设置pager语句:" + sql);
		return CommonUtil.getCount(new CommonSql(sql));
	}
	
	/**
	 * 根据供应商ID，查找是否有status为2（未付款）的订单
	 * @param providerId 供应商ID
	 * @return 存在未付款返回true 不存在返回false
	 */
	public boolean existNotDone (int providerId){
		String sql = "select count(1)  from mk_order where ispay=2 and c_pid in (select id from c_provide where provider_id="
				+ providerId +")";
		//System.out.println(sql);
		int row = od.existNotDone(new CommonSql(sql));
		return row > 0;
	}
	
}

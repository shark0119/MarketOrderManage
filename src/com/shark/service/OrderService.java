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
	 * ��Ӷ���
	 * @return ʧ�ܷ���false �ɹ�����true
	 */
	public boolean addOrder (Order order){
		String sql = " insert into mk_order (id, time, ispay, money, counts, order_desc, c_pid)"
				+ " values (seq_order.nextval, sysdate,? ,? ,? ,? ,?)";
		return 1 == od.updateOrder(new CommonSql(sql, order.getIspay(), order.getMoney(), order.getCount(),
				order.getDesc(), order.getC_pid()));
	}
	
	/**
	 * �޸Ķ���
	 * @return ʧ�ܷ���false �ɹ�����true 
	 */
	public boolean updateOrder (Order order){
		String sql = " update mk_order set ispay=?, money=?, counts=?, order_desc=?, c_pid=? where id = ? ";
		int row = od.updateOrder(new CommonSql(sql, order.getIspay(), order.getMoney(), 
						order.getCount(), order.getDesc(),order.getC_pid(), order.getId()));
		return row == 1? true: false;
	}
	/**
	 * 
	 * @param id ����IDɾ������
	 * @return �ɹ�����true ʧ�ܷ���false
	 */
	public boolean removeOrder (int id){
		String sql = "delete from mk_order where id = ?";
		int row = od.updateOrder(new CommonSql(sql, id));
		return row == 1? true: false;
	}
	/**
	 * ����ID�����Ҷ���
	 * @param id
	 * @return ʧ�ܻ�Ϊ�鵽����null�� �ɹ����ض���ʵ��
	 */
	public Order getOrder (int id){
		String sql = "select * from mk_order where id = ? ";
		Order order = od.getOrder(new CommonSql(sql, id));
		order.setProductName(CommonUtil.getProService().getProviderByC_Pid(order.getC_pid()).getName());
		return order;
	}
	/**
	 * ��������ҳ��ѯ
	 * @param pager �����˷�ҳ��Ϣ��ʵ��
	 * @return ���ݿ���󷵻�null, �޽����ɹ����ض�������
	 */
	public List<Order> getOrderList (Pager pager){
		return getOrderList (pager, null, -1, -1);
	}
	/**
	 * ������ҳ��ѯ
	 * @param pager �����˷�ҳ��Ϣ��ʵ��
	 * @param condition �����������Ķ���ʵ��
	 * @param productId ��ƷID	�޴�����-1
	 * @param providerId ��Ӧ��ID �޴�����-1
	 * @return ���ݿ���󷵻�null, �޽����ɹ����ض�������
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
		
		System.out.println("��ҳ��ѯ���: "+sql + " \n\tbegin :"+ begin +" end:"+end);
		
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
	 * ��������ѯ��������
	 * @return ʧ�ܷ���-1���ɹ����ظ���
	 */
	public int getTotal (){
		return getTotal(null, -1, -1);
	}
	/**
	 * ������ѯ��������
	 * @param condition ��װ��������Ϣ�Ķ���ʵ��
	 * @param productId ��ƷID	�޴�����-1
	 * @param providerId ��Ӧ��ID �޴�����-1
	 * @return ʧ�ܷ���-1���ɹ����ظ���
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
		System.out.println("\n����pager���:" + sql);
		return CommonUtil.getCount(new CommonSql(sql));
	}
	
	/**
	 * ���ݹ�Ӧ��ID�������Ƿ���statusΪ2��δ����Ķ���
	 * @param providerId ��Ӧ��ID
	 * @return ����δ�����true �����ڷ���false
	 */
	public boolean existNotDone (int providerId){
		String sql = "select count(1)  from mk_order where ispay=2 and c_pid in (select id from c_provide where provider_id="
				+ providerId +")";
		//System.out.println(sql);
		int row = od.existNotDone(new CommonSql(sql));
		return row > 0;
	}
	
}

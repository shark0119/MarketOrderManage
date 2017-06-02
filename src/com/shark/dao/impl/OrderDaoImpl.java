package com.shark.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shark.dao.BaseDao;
import com.shark.dao.OrderDao;
import com.shark.entity.Order;
import com.shark.sql.GenerateSql;

public class OrderDaoImpl extends BaseDao implements OrderDao {

	@Override
	public List<Order> getOrderList(GenerateSql gs) {
		if (!this.getConnection()){
			return null;
		}
		return rset2Order(this.executeQuery(gs.getSql(), gs.getPara()));
	}

	@Override
	public Order getOrder(GenerateSql gs) {
		if (!this.getConnection()){
			return null;
		}
		List <Order> oList = rset2Order(this.executeQuery(gs.getSql(), gs.getPara()));
		if  (oList.size() == 0)
			return null;
		return oList.get(0);
	}

	@Override
	public int updateOrder(GenerateSql gs) {
		if (!this.getConnection()){
			return -1;
		}
		return this.executeUpdate(gs.getSql(), gs.getPara());
	}
	/**
	 * 将结果集转化为订单集合
	 * @return 供应商集合，成功返回结果集，失败返回null，无结果仍返回结果集，但长度为0
	 */
	protected List<Order> rset2Order (ResultSet rset){
		List <Order> oList = null;
		try {
			Order o;
			oList = new ArrayList<>();
			while (rset.next()){
				o = new Order();
				o.setId(rset.getInt("id"));
				o.setCtime(rset.getTimestamp("time"));
				o.setIspay(rset.getInt("ispay"));
				o.setMoney(rset.getInt("money"));
				o.setCount(rset.getInt("counts"));
				o.setDesc(rset.getString("order_desc"));
				o.setC_pid(rset.getInt("c_pid"));
				oList.add(o);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return oList;
	}
	public int existNotDone (GenerateSql gs){
		if (!this.getConnection()){
			return -1;
		}
		ResultSet rset = this.executeQuery(gs.getSql(), gs.getPara());
		int row =0;
		try {
			if (rset.next()){
				row = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return row;
	}
}

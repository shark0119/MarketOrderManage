package com.shark.dao;

import java.util.List;

import com.shark.entity.Order;
import com.shark.sql.GenerateSql;

public interface OrderDao {

	/**
	 * 获取订单集合
	 * @param gs 待执行的sql语句
	 * @return 供应商集合，成功返回结果集，失败返回null，无结果仍返回结果集，但长度为0
	 */
	List<Order> getOrderList (GenerateSql gs);
	/**
	 * 获取结果集的第一个用户
	 * @param 待执行的SQL语句及参数
	 * @return 成功返回Order ，无结果或失败返回null
	 */
	Order getOrder (GenerateSql gs);
	
	/**
	 * 修改删除操作
	 * @param gs 待执行的SQL语句及参数
	 * @return 返回受影响的行数，失败返回-1
	 */
	int updateOrder (GenerateSql gs);
	/**
	 * 根据供应商ID，查找是否有status为2（未付款）的订单
	 * @param providerId 供应商ID
	 * @return 存在未付款返回个数 不存在返回-1
	 */
	public int existNotDone (GenerateSql gs);	
}

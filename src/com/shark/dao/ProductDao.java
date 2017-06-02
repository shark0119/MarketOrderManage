package com.shark.dao;

import java.util.List;

import com.shark.entity.Product;
import com.shark.sql.GenerateSql;

public interface ProductDao {
	/**
	 * 查询单个产品实体
	 * @param gs
	 * @return 失败或数据库错误返回null，成功返回产品实体
	 */
	Product getProduct (GenerateSql gs);
	/**
	 * 查询产品列表
	 * @param gs
	 * @return 数据库错误返回null，无结果或成功都返回集合
	 */
	List <Product> getProductList (GenerateSql gs);
}

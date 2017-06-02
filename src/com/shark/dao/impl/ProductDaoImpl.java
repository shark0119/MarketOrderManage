package com.shark.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shark.dao.BaseDao;
import com.shark.dao.ProductDao;
import com.shark.entity.Product;
import com.shark.sql.GenerateSql;

public class ProductDaoImpl extends BaseDao implements ProductDao {

	@Override
	public Product getProduct(GenerateSql gs) {
		if (!this.getConnection()){
			return null;
		}
		List <Product> pList = rset2ProductList(this.executeQuery(gs.getSql(), gs.getPara()));
		if (pList == null || pList.size() == 0)
			return null;
		return pList.get(0);
	}

	@Override
	public List<Product> getProductList(GenerateSql gs) {
		if (!this.getConnection()){
			return null;
		}
		return rset2ProductList(this.executeQuery(gs.getSql(), gs.getPara()));
	}
	/**
	 * 查询产品列表
	 * @param gs
	 * @return 数据库错误返回null，无结果或成功都返回集合
	 */
	protected List <Product> rset2ProductList (ResultSet rset){
		List <Product> pList = null;
		try {
			Product p = null;
			pList = new ArrayList<>();
			while (rset.next()){
				p = new Product();
				p.setId(rset.getInt("id"));
				p.setName(rset.getString("name"));
				p.setUnit(rset.getString("unit"));
				pList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return pList;
	}

}

package com.shark.service;

import java.util.List;

import com.shark.dao.ProductDao;
import com.shark.dao.impl.ProductDaoImpl;
import com.shark.entity.Product;
import com.shark.sql.CommonSql;

public class ProductService {
	private ProductDao pd ;
	
	public ProductService() {
		pd = new ProductDaoImpl();
	}
	/**
	 * @see ProductDao#getProduct(com.shark.sql.GenerateSql)
	 */
	public Product getProductByC_Pid (int c_pid){
		String sql = " select distinct pd.* from mk_product pd, c_provide pr where "
				+ "pd.id = pr.product_id and pr.id =? ";
		System.out.println(sql);
		return pd.getProduct(new CommonSql(sql, c_pid));
	}
	
	/**
	 * @see  ProductDao#getProductList(com.shark.sql.GenerateSql)
	 */
	public List <Product> getProductList (){
		String sql = "select * from mk_product";
		return pd.getProductList(new CommonSql(sql));
	}
	/**
	 * 查询某个供应商供应的所有产品
	 * @param providerId 
	 * @return
	 * @see ProductDao#getProductList(com.shark.sql.GenerateSql)
	 */
	public List <Product> getProductList (int providerId){
		String sql = "select distinct pd.* from mk_product pd, c_provide pr , mk_provider pv "
				+ " where pd.id= pr.product_id and pv.id = pr.provider_id "
				+ "and pv.id= ?";
		return pd.getProductList(new CommonSql(sql, providerId));
	}
}

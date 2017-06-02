package com.shark.dao;

import java.util.List;

import com.shark.entity.Product;
import com.shark.sql.GenerateSql;

public interface ProductDao {
	/**
	 * ��ѯ������Ʒʵ��
	 * @param gs
	 * @return ʧ�ܻ����ݿ���󷵻�null���ɹ����ز�Ʒʵ��
	 */
	Product getProduct (GenerateSql gs);
	/**
	 * ��ѯ��Ʒ�б�
	 * @param gs
	 * @return ���ݿ���󷵻�null���޽����ɹ������ؼ���
	 */
	List <Product> getProductList (GenerateSql gs);
}

package com.shark.dao;

import java.util.List;

import com.shark.entity.Order;
import com.shark.sql.GenerateSql;

public interface OrderDao {

	/**
	 * ��ȡ��������
	 * @param gs ��ִ�е�sql���
	 * @return ��Ӧ�̼��ϣ��ɹ����ؽ������ʧ�ܷ���null���޽���Է��ؽ������������Ϊ0
	 */
	List<Order> getOrderList (GenerateSql gs);
	/**
	 * ��ȡ������ĵ�һ���û�
	 * @param ��ִ�е�SQL��估����
	 * @return �ɹ�����Order ���޽����ʧ�ܷ���null
	 */
	Order getOrder (GenerateSql gs);
	
	/**
	 * �޸�ɾ������
	 * @param gs ��ִ�е�SQL��估����
	 * @return ������Ӱ���������ʧ�ܷ���-1
	 */
	int updateOrder (GenerateSql gs);
	/**
	 * ���ݹ�Ӧ��ID�������Ƿ���statusΪ2��δ����Ķ���
	 * @param providerId ��Ӧ��ID
	 * @return ����δ����ظ��� �����ڷ���-1
	 */
	public int existNotDone (GenerateSql gs);	
}

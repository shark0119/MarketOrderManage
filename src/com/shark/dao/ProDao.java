package com.shark.dao;

import java.util.List;

import com.shark.entity.Provider;
import com.shark.sql.GenerateSql;

/**
 * ��Ӧ�̽ӿ�
 * @author Shark
 */
public interface ProDao {
	/**
	 * ��ȡ��Ӧ�̼���
	 * @param gs ��ִ�е�sql���
	 * @return ��Ӧ�̼��ϣ��ɹ����ؽ������ʧ�ܷ���null���޽���Է��ؽ������������Ϊ0
	 */
	List <Provider> getProList (GenerateSql gs);

	/**
	 * ��ȡ������ĵ�һ���û�
	 * @param ��ִ�е�SQL��估����
	 * @return �ɹ�����Provider ���޽����ʧ�ܷ���null
	 */
	Provider getProvider (GenerateSql gs);
	
	/**
	 * �޸�ɾ�������������ӣ�
	 * @param gs ��ִ�е�SQL��估����
	 * @return ������Ӱ���������ʧ�ܷ���-1
	 */
	int updateProvider (GenerateSql gs);
}

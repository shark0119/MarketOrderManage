package com.shark.dao;

import java.util.List;

import com.shark.entity.User;
import com.shark.sql.GenerateSql;

public interface UserDao {
	
	/**
	 * @param gs ����ģ� ��ִ�е�SQL����Լ���ռλ����
	 * @return �ɽ����ת��������ʵ�弯��,���ݿ�ʧ�ܷ���null, ��ѯ�ɹ����ؼ��ϣ�����Ԫ�ظ���Ϊ0��ʾ�޶�Ӧ��¼
	 */
	List <User> getUserList (GenerateSql gs);
	/**
	 * ���ڲ�ѯֻ��һ�������ֻ���һ����������
	 * ������ getUserList���棬������Ч��Ҫ��
	 * @param gs ����ģ� ��ִ�е�SQL����Լ���ռλ����
	 * @return ��ѯ�����û�ʵ��,�޽����ʧ�ܷ���Nullֵ
	 */
	User getUser (GenerateSql gs);
	/**
	 * ���,ɾ��,�޸��û�
	 * @return ���ݿ�ʧ�ܷ���-1 �ɹ�������Ӱ�������
	 */
	int updateUser (GenerateSql gs);

	
}

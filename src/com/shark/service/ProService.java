package com.shark.service;

import java.util.List;

import com.shark.dao.ProDao;
import com.shark.dao.impl.ProDaoImpl;
import com.shark.entity.Pager;
import com.shark.entity.Provider;
import com.shark.sql.CommonSql;
import com.shark.util.CommonUtil;

public class ProService {
	private ProDao pd;
	public ProService() {
		pd = new ProDaoImpl();
	}
	/**
	 * ��ӹ�Ӧ��
	 * @return �ɹ�Ϊtrue��ʧ��Ϊfalse
	 */
	public boolean addPro (Provider pro){
		String sql = "insert into mk_provider (id, name, contact, pro_desc, phone, address, fax) "
				+ "values (seq_provider.nextval, ?, ?, ?, ?, ?, ? ) ";
		int row = pd.updateProvider(new CommonSql(sql, pro.getName(), pro.getContact(), pro.getDesc(),
				pro.getPhone(), pro.getAddress(), pro.getFax()));
		return row == 1 ;
	}
	/**
	 * ���иù�Ӧ���ж���Ϊδ����ģ�����ɾ��
	 * ����status��Ϊ -1 ��Ϊɾ��
	 * @param id ��ɾ����ID
	 * @return �ɹ�Ϊtrue��ʧ��Ϊfalse
	 */
	public boolean removePro (int id){
		if (CommonUtil.getOrderService().existNotDone(id))
			return false;
		String sql = "delete from mk_provider where id =? ";
		int row = pd.updateProvider(new CommonSql(sql, id));
		return row == 1;
	}
	/**
	 * ���¹�Ӧ��
	 * @return �ɹ�Ϊtrue��ʧ��Ϊfalse
	 */
	public boolean updatePro (Provider pro){
		String sql = " update mk_provider set name=?, contact=?, pro_desc=?, phone=?, address=?, fax=? where id = ? ";
		int row = pd.updateProvider(new CommonSql(sql, pro.getName(), pro.getContact(), pro.getDesc(),
				pro.getPhone(), pro.getAddress(), pro.getFax(), pro.getId()));
		return row == 1;
	}
	/**
	 * �鿴ָ��ID�Ĺ�Ӧ��
	 * @param id
	 * @return
	 */
	public Provider checkPro (int id){
		String sql = " select * from mk_provider where id=? ";
		return pd.getProvider(new CommonSql(sql, id));
	}
	/**
	 * ��������ͨ��ҳ
	 * @param page ��ҳ��Ϣ
	 * @return ��Ӧ�̼���
	 */
	public List<Provider> getProList (Pager page){
		return getProList(page, null);
	}
	/**
	 * 
	 * ������ͨ��ҳ
	 * @param page ��ҳ��Ϣ
	 * @param condition ����������ʵ����
	 * @return ��Ӧ�̼���
	 */
	public List<Provider> getProList (Pager page, Provider condition){
		page.setTotalCount(getTotal(condition));
		String sql = "select * from (select t.*, rownum rn from (select * from mk_provider where status=1 ";
		String cstr = " ";
		if (condition != null){
			if (!CommonUtil.isEmpty(condition.getName())){
				cstr += " and name like '%"+ condition.getName() +"%'";
			}
		}
		sql += cstr;
		sql += " ) t) where rn>? and rn <=? ";
		return pd.getProList(new CommonSql(sql, 
								page.getPageSize() * (page.getPageIndex() - 1), 
								page.getPageSize()*page.getPageIndex()));
	}
	/**
	 * @return ��ȡ����
	 */
	public int getTotal (){
		return getTotal (null);
	}
	/**
	 * @param condition ����������ʵ���࣬���ݴ���������ȡ��Ӧ�̼��ϵ�����
	 * @return ��ȡ����
	 */
	public int getTotal (Provider condition){
		String sql = " select count (1) from mk_provider where 1=1 ";
		if (condition != null){
			if (!CommonUtil.isEmpty(condition.getName())){
				sql = sql + " and name like '%" + condition.getName() + "%'";
			}
			return CommonUtil.getCount(new CommonSql(sql));
		}
		return CommonUtil.getCount(new CommonSql(sql));		
	}
	/**
	 * 
	 * @param id ��Ӧ��ID
	 * @return �ɹ�����Provider ���޽����ʧ�ܷ���null
	 */
	public Provider getProvider(int id) {
		String sql = "select * from mk_provider where id= ?";
		return pd.getProvider(new CommonSql(sql, id));
	}
}

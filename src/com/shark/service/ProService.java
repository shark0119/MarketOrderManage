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
	 * 添加供应商
	 * @return 成功为true，失败为false
	 */
	public boolean addPro (Provider pro){
		String sql = "insert into mk_provider (id, name, contact, pro_desc, phone, address, fax) "
				+ "values (seq_provider.nextval, ?, ?, ?, ?, ?, ? ) ";
		int row = pd.updateProvider(new CommonSql(sql, pro.getName(), pro.getContact(), pro.getDesc(),
				pro.getPhone(), pro.getAddress(), pro.getFax()));
		return row == 1 ;
	}
	/**
	 * 若有该供应商有订单为未结算的，不予删除
	 * 否则将status置为 -1 即为删除
	 * @param id 待删除的ID
	 * @return 成功为true，失败为false
	 */
	public boolean removePro (int id){
		if (CommonUtil.getOrderService().existNotDone(id))
			return false;
		String sql = "delete from mk_provider where id =? ";
		int row = pd.updateProvider(new CommonSql(sql, id));
		return row == 1;
	}
	/**
	 * 更新供应商
	 * @return 成功为true，失败为false
	 */
	public boolean updatePro (Provider pro){
		String sql = " update mk_provider set name=?, contact=?, pro_desc=?, phone=?, address=?, fax=? where id = ? ";
		int row = pd.updateProvider(new CommonSql(sql, pro.getName(), pro.getContact(), pro.getDesc(),
				pro.getPhone(), pro.getAddress(), pro.getFax(), pro.getId()));
		return row == 1;
	}
	/**
	 * 查看指定ID的供应商
	 * @param id
	 * @return
	 */
	public Provider checkPro (int id){
		String sql = " select * from mk_provider where id=? ";
		return pd.getProvider(new CommonSql(sql, id));
	}
	/**
	 * 无条件普通分页
	 * @param page 分页信息
	 * @return 供应商集合
	 */
	public List<Provider> getProList (Pager page){
		return getProList(page, null);
	}
	/**
	 * 
	 * 条件普通分页
	 * @param page 分页信息
	 * @param condition 包含条件的实体类
	 * @return 供应商集合
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
	 * @return 获取总数
	 */
	public int getTotal (){
		return getTotal (null);
	}
	/**
	 * @param condition 包含条件的实体类，根据此条件来获取供应商集合的总数
	 * @return 获取总数
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
	 * @param id 供应商ID
	 * @return 成功返回Provider ，无结果或失败返回null
	 */
	public Provider getProvider(int id) {
		String sql = "select * from mk_provider where id= ?";
		return pd.getProvider(new CommonSql(sql, id));
	}
}

package com.shark.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shark.dao.BaseDao;
import com.shark.dao.ProDao;
import com.shark.entity.Provider;
import com.shark.sql.GenerateSql;

public class ProDaoImpl extends BaseDao implements ProDao {

	@Override
	public List<Provider> getProList(GenerateSql gs) {
		if (!this.getConnection())
			return null;
		return rset2ProList(this.executeQuery(gs.getSql(), gs.getPara()));
	}

	@Override
	public Provider getProvider(GenerateSql gs) {
		if (!this.getConnection())
			return null;
		List<Provider> list = rset2ProList(this.executeQuery(gs.getSql(), gs.getPara()));
		if (list.size() != 0)
			return list.get(0);
		return null;
	}

	@Override
	public int updateProvider(GenerateSql gs) {
		if (!this.getConnection())
			return -1;
		return this.executeUpdate(gs.getSql(), gs.getPara());
	}
	/**
	 * 将结果集转化为实体类集合
	 * @param rset
	 * @return
	 */
	protected List<Provider> rset2ProList (ResultSet rset){
		List <Provider> pList = null;
		try {
			pList = new ArrayList<>();
			Provider p =null;
			while (rset.next()){
				p = new Provider();
				p.setId(rset.getInt("id"));
				p.setName(rset.getString("name"));
				p.setContact(rset.getString("contact"));
				p.setDesc(rset.getString("pro_desc"));
				p.setPhone(rset.getString("phone"));
				p.setAddress(rset.getString("address"));
				p.setCdate(rset.getTimestamp("createDate"));
				p.setFax(rset.getString("fax"));
				pList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return pList;
	}
}

package com.shark.dao;

import java.util.List;

import com.shark.entity.Provider;
import com.shark.sql.GenerateSql;

/**
 * 供应商接口
 * @author Shark
 */
public interface ProDao {
	/**
	 * 获取供应商集合
	 * @param gs 待执行的sql语句
	 * @return 供应商集合，成功返回结果集，失败返回null，无结果仍返回结果集，但长度为0
	 */
	List <Provider> getProList (GenerateSql gs);

	/**
	 * 获取结果集的第一个用户
	 * @param 待执行的SQL语句及参数
	 * @return 成功返回Provider ，无结果或失败返回null
	 */
	Provider getProvider (GenerateSql gs);
	
	/**
	 * 修改删除操作（？增加）
	 * @param gs 待执行的SQL语句及参数
	 * @return 返回受影响的行数，失败返回-1
	 */
	int updateProvider (GenerateSql gs);
}

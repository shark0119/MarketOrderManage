package com.shark.dao;

import java.util.List;

import com.shark.entity.User;
import com.shark.sql.GenerateSql;

public interface UserDao {
	
	/**
	 * @param gs 必需的， 待执行的SQL语句以及其占位参数
	 * @return 由结果集转化而来的实体集合,数据库失败返回null, 查询成功返回集合，集合元素个数为0表示无对应记录
	 */
	List <User> getUserList (GenerateSql gs);
	/**
	 * 用于查询只有一条结果或只查第一条结果的情况
	 * 可以由 getUserList代替，但比其效率要高
	 * @param gs 必需的， 待执行的SQL语句以及其占位参数
	 * @return 查询到的用户实体,无结果或失败返回Null值
	 */
	User getUser (GenerateSql gs);
	/**
	 * 删除,修改用户
	 * @return 数据库失败返回-1 成功返回受影响的行数
	 */
	int updateUser (GenerateSql gs);

	/**
	 * 添加用户
	 * 以存储过程addUser进行数据插入，并获取数据库自动生成的值
	 *function addUser 
	 *(name, pwd, sex, birth, mobile, add, rid)return id
	 * @return 插入成功返回用户，否则返回null,包含数据库自动生成的值，并非全部信息
	 */
	User addUser (GenerateSql gs);
}

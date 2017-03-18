/********************************
 *  
 * @Copyright (c) by  
 * @Create Author: Tide
 * @Create Date: Jun 28, 2011 
 * @Modify Date:
 * @Version: V 1.0 
 */ 

package com.syscore.commons.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBasDao<T> {
	/**
	 * 主要用于SQL语句的数据集插入
	 * @param Entity
	 * @return
	 */
	public Object insert(T Entity);
	
	/**
	 * 增加一个对象
	 * @param statement
	 * @param Entity
	 * @return
	 */
	public Object insert(String statement, T Entity);
	
	/**
	 * 增加对象，参数在Map中
	 * @param statement
	 * @param paramMap
	 * @return
	 */
	public Object insert(String statement, Map<String,Object> paramMap);
	/**
	 * 删除数据
	 * @param Entity
	 * @return
	 */
	public Integer delete(T Entity);
	/**
	 * 删除数据
	 * @param statement
	 * @param Entity
	 * @return
	 */
	public Integer delete(String statement, T Entity);
	/**
	 * 删除数据
	 * @param statement
	 * @param ID
	 * @return
	 */
	public Integer delete(String statement, Serializable ID);
	/**
	 * 修改数据
	 * @param statement
	 * @return
	 */
	public Integer update(T Entity);
	/**
	 * 修改数据
	 * @param statement
	 * @param Entity
	 * @return
	 */
	public Integer update(String statement, T Entity);
	/**
	 * 修改数据
	 * @param statement
	 * @param paramMap
	 * @return
	 */
	public Integer update(String statement, Map<String,Object> paramMap);
	
	/**
	 * 获取Object对象
	 * @param statement
	 * @return
	 */
	public Object selectObject(String statement);
	
	/**
	 * 获取Entity对象
	 * @param statment
	 * @return
	 */
	public T selectEntity(T Entity); 
	
	/**
	 * 获取Entity对象
	 * @param statement
	 * @param Entity
	 * @return
	 */ 
	public Object selectObject(String statement, T Entity);
	
	/**
	 * 获取Entity对象
	 * @param statement
	 * @param Entity
	 * @return
	 */ 
	public T selectEntity(String statement, T Entity);
	
	/**
	 * 获取Object对象
	 * @param statement
	 * @param paramMap
	 * @return
	 */
	public Object selectObject(String statement, Map<String,Object> paramMap);
	
	/**
	 * 获取Entity对象
	 * @param statement
	 * @param paramMap
	 * @return
	 */
	public T selectEntity(String statement, Map<String,Object> paramMap);
	/**
	 * 获取Entity对象
	 * @param statement
	 * @param ID
	 * @return
	 */
	public T selectEntity(String statement, Serializable ID);	
 
	/**
	 * 获取Entity数据
	 * @param statement
	 * @return
	 */
	public List<T> selectEntitys(T Entity);
	/**
	 * 获取Entity数据
	 * @param statement	 
	 * @return
	 */
	public List<T> selectEntitys(String statement);
	/**
	 * 获取Entity数据
	 * @param statement
	 * @param Entity
	 * @return
	 */
	public List<T> selectEntitys(String statement, T Entity);
	/**
	 * 获取Entity数据
	 * @param statement
	 * @param paramMap
	 * @return
	 */
	public List<T> selectEntitys(String statement, Map<String,Object> paramMap);
	/**
	 * 获取Entity数据
	 * @param statement
	 * @param ID
	 * @return
	 */
	public List<T> selectEntitys(String statement, Serializable ID); 
	
	/**
	 * 获取分页查询
	 * @param statement
	 * @param paramMap
	 * @return
	 */
	//废弃的原因是不同的数据库计算方式不一样，故把此变动的地方用PageUtil专门处理
	//public Page<T> selectPageEntitys(String statement, Map<String,Object> paramMap);
	
	
	/**
	 * 获取存储过程结果	
	 * @param statementId
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> selectProc(String statementId, Map<String, Object> paramMap);
	
	/**
	 * 批量修改	 
	 * @param statementId
	 * @param list
	 */
	public void batchUpdate(final String statementId, final List<T> list);

	/**
	 * 批量增加
	 * @param statementId
	 * @param list
	 */
	public void batchInsert(final String statementId, final List<T> list);

	/**
	 * 批量删除
	 * @param statementId
	 * @param list
	 */
	public void batchDelete(final String statementId, final List<T> list);

}

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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.syscore.commons.model.BasInfo;
import com.syscore.commons.utils.generate.DefaultSequenceGenerator;
import com.syscore.constants.ConstantsWeb;

@SuppressWarnings("unchecked")
public class BasDao<T> extends SqlMapClientDaoSupport implements IBasDao<T> {

	protected DefaultSequenceGenerator sequenceGenerator = DefaultSequenceGenerator.getInstance();
	
	public Integer delete(T Entity) {
		return this.getSqlMapClientTemplate().delete(ConstantsWeb.getDaoPrefix(Entity.getClass())+"delete",Entity);		
	}

	public Integer delete(String statement, T Entity) {
		return this.getSqlMapClientTemplate().delete(statement, Entity);
	}

	public Integer delete(String statement, Serializable ID) {
		return this.getSqlMapClientTemplate().delete(statement, ID);
	}
 
	public T selectEntity(T Entity) {
		return (T) this.getSqlMapClientTemplate().queryForObject(ConstantsWeb.getDaoPrefix(Entity.getClass())+"selectEntity",Entity);
	}

	public T selectEntity(String statement, T Entity) {
		return (T) this.getSqlMapClientTemplate().queryForObject(statement,Entity);
	}

	public T selectEntity(String statement, Map<String, Object> paramMap) {
		return (T) this.getSqlMapClientTemplate().queryForObject(statement,paramMap);
	}

	public T selectEntity(String statement, Serializable ID) {
		return (T) this.getSqlMapClientTemplate().queryForObject(statement,ID);
	}

	public List<T> selectEntitys(T Entity) {
		return this.getSqlMapClientTemplate().queryForList(ConstantsWeb.getDaoPrefix(Entity.getClass())+"selectEntitys",Entity);
	}
	
	public List<T> selectEntitys(String statement) {
		return this.getSqlMapClientTemplate().queryForList(statement);
	} 

	public List<T> selectEntitys(String statement, T Entity) {
		return this.getSqlMapClientTemplate().queryForList(statement,Entity);
	}

	public List<T> selectEntitys(String statement, Map<String, Object> paramMap) {
		return this.getSqlMapClientTemplate().queryForList(statement,paramMap);
	}

	public List<T> selectEntitys(String statement, Serializable ID) {
		return this.getSqlMapClientTemplate().queryForList(statement,ID);
	} 

	public Object insert(T Entity) {		
		String id = sequenceGenerator.uuid();
		BasInfo basInfo = (BasInfo)Entity;
		basInfo.setId(id);
		this.getSqlMapClientTemplate().insert(ConstantsWeb.getDaoPrefix(Entity.getClass())+"insert",Entity);
		return id;		
	} 

	public Object insert(String statement, T Entity) {
		return this.getSqlMapClientTemplate().insert(statement,Entity);
	}

	public Object insert(String statement, Map<String, Object> paramMap) {
		return this.getSqlMapClientTemplate().insert(statement,paramMap);
	}

	public Integer update(T Entity) {
		return this.getSqlMapClientTemplate().update(ConstantsWeb.getDaoPrefix(Entity.getClass())+"update",Entity);
	}

	public Integer update(String statement, T Entity) {
		return this.getSqlMapClientTemplate().update(statement,Entity);
	}

	public Integer update(String statement, Map<String, Object> paramMap) {
		return this.getSqlMapClientTemplate().update(statement,paramMap);
	}
	
	public Object selectObject(String statement){
		return this.getSqlMapClientTemplate().queryForObject(statement);
	}
	
	public Object selectObject(String statement, Map<String, Object> paramMap){
		return this.getSqlMapClientTemplate().queryForObject(statement,paramMap);
	}

	public Object selectObject(String statement, T Entity) {
		return this.getSqlMapClientTemplate().queryForObject(statement,Entity);
	}  
 
	/*
	 * 废弃的原因是不同的数据库计算方式不一样，故把此变动的地方用PageUtil专门处理
	public Page selectPageEntitys(String statement,
			Map<String, Object> paramMap) {
		//计算起始数据和结束数据
		Integer pageGrid = (Integer)paramMap.get("page");
		Integer rowsGrid = (Integer)paramMap.get("rows");
		if("mysql".equals(ConstantsWeb.databaseType)){ 
			Integer beginRow = (pageGrid-1)*rowsGrid;
			Integer endRow = rowsGrid;
			paramMap.put("beginRow", beginRow);
			paramMap.put("endRow", endRow);
		}else if("oracle".equals(ConstantsWeb.databaseType)){
			Integer beginRow = (pageGrid-1)*rowsGrid;
			Integer endRow = rowsGrid;
			paramMap.put("beginRow", beginRow);
			paramMap.put("endRow", endRow);
		}else if("sqlserver".equals(ConstantsWeb.databaseType)){
			Integer beginRow = (pageGrid-1)*rowsGrid; 
			paramMap.put("beginRow", beginRow);
			paramMap.put("pageSize", rowsGrid);
		}
		
		Page page = new Page();
		List<T> listValues = this.getSqlMapClientTemplate().queryForList(statement,paramMap);
		Integer totalCount = (Integer) this.getSqlMapClientTemplate().queryForObject(statement+"Count",paramMap); 
		page.sEntityotalCount(totalCount);
		page.setRtnObject(listValues);
		return page;
	}
 */
	public Map<String, Object> selectProc(String statementId, Map<String, Object> paramMap) {

		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		
		//oracle游标返回值直接放在了参数里
		if("oracle".equals(ConstantsWeb.databaseType))
			selectEntitys(statementId, paramMap);
		
		if("mysql".equals(ConstantsWeb.databaseType)){
			List<T> infos = selectEntitys(statementId, paramMap);
			paramMap.put("data", infos);
		}
		 
		//sybase存储过程		 
		if("sybase".equals(ConstantsWeb.databaseType)){
			try {
				getSqlMapClientTemplate().getDataSource().getConnection().setAutoCommit(true);
				List<T> infos = selectEntitys(statementId + "_sybase", paramMap);
				paramMap.put("data", infos);
				getSqlMapClientTemplate().getDataSource().getConnection().setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
			
		return paramMap;
	}
 
	public void batchUpdate(final String statementId, final List<T> list) { 
		if (list != null) {
			this.getSqlMapClientTemplate().execute(
				new SqlMapClientCallback(){
					public Object doInSqlMapClient(SqlMapExecutor executor)	throws SQLException {
						int batch = 0;
						executor.startBatch();
						for (int i = 0, n = list.size(); i < n; i++) {
							executor.update(statementId, list.get(i));
							// 每500条批量提交一次	
							if (batch == 500) {
								executor.executeBatch();
								batch = 0;
							}
							batch++;
						}
						executor.executeBatch();
						return null;
					}
				});
		} 

	}
 
	public void batchInsert(final String statementId, final List<T> list) { 
		if (list != null) {
			this.getSqlMapClientTemplate().execute(
				new SqlMapClientCallback() {
					public Object doInSqlMapClient(SqlMapExecutor executor)	throws SQLException {
						int batch = 0;
						executor.startBatch();
						for (int i = 0, n = list.size(); i < n; i++) {
							String id = sequenceGenerator.uuid();
							BasInfo basInfo = (BasInfo)list.get(i);
							if(basInfo.getId()==null){
								basInfo.setId(id);
							}
							
							executor.insert(statementId, list.get(i));
							// 每500条批量提交一次。
							if (batch == 500) {
								executor.executeBatch();
								batch = 0;
							}
							batch++;
						}
						executor.executeBatch();
						return null;
					}
				});
		}
	}
	 
 
	public void batchDelete(final String statementId, final List<T> list) {
		if (list != null) {
			this.getSqlMapClientTemplate().execute(
				new SqlMapClientCallback() {
					public Object doInSqlMapClient(SqlMapExecutor executor)	throws SQLException {
						int batch = 0;
						executor.startBatch();
						for (int i = 0, n = list.size(); i < n; i++) {
							executor.delete(statementId, list.get(i));
							// 每500条批量提交一次。
							if (batch == 500) {
								executor.executeBatch();
								batch = 0;
							}
							batch++;
						}
						executor.executeBatch();
						return null;
					}
				});
		} 
	} 
	 
}

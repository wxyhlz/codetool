/********************************
 *  
 * @Copyright (c) by  
 * @Create Author: Tide
 * @Create Date: Jun 28, 2011 
 * @Modify Date:
 * @Version: V 1.0 
 */ 

package  com.syscore.commons.service;

import java.util.List;

import com.syscore.commons.dao.IBasDao;
import com.syscore.commons.model.BasInfo;
import com.syscore.commons.utils.generate.DefaultSequenceGenerator;
 

public class BasService<T extends BasInfo,DAO extends IBasDao<T>> implements IBasService<T> {
	
	protected DAO dao;
	
	private DefaultSequenceGenerator dsg = DefaultSequenceGenerator.getInstance();

	/**
	 * @return the dao
	 */
	public DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(DAO dao) {
		this.dao = dao;
	}
	
	/**
	 * 设置主键
	 * @param list
	 * @return
	 */
	public List<T>  setEntitiesID(List<T> list) {
		
		for(T v : list){
			v.setId(dsg.uuid());
		}
		
		return list;		
	}
	
	/**
	 * 设置主键
	 * @param list
	 * @return
	 */
	public T setEntityID(T entityInfo) {
		
		entityInfo.setId(dsg.uuid());
		
		return entityInfo;
	}

}

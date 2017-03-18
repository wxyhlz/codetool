/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: SysuserroleService.java
 * 
 */
package com.syscore.system.sysuserrole.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syscore.commons.except.BusinessException;
import com.syscore.commons.service.BasService;
import com.syscore.system.sysuserrole.dao.ISysuserroleDao;
import com.syscore.system.sysuserrole.model.SysuserroleInfo;
import com.syscore.system.sysuserrole.service.ISysuserroleService;

/**
 * @ClassName : SysuserroleService
 * 
 * @Description : 对应—人员角色_SERVICE
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class SysuserroleService extends BasService<SysuserroleInfo, ISysuserroleDao> implements ISysuserroleService {
	
	/**
	 * 新增
	 * @param entityInfo
	 */
	public void insertSysuserrole(SysuserroleInfo entityInfo){
		//判断对象是否存在
		SysuserroleInfo info = new SysuserroleInfo();
		SysuserroleInfo vInfo = dao.selectEntity(info);
		if(vInfo!=null){ 
			throw new BusinessException("已经存在，不允许重复增加。");
		}else{
			//新增
			entityInfo = setEntityID(entityInfo);
			dao.insert(entityInfo);
		}
	}


	/**
	 * 删除
	 * @param entityInfo
	 */
	public void deleteSysuserrole(SysuserroleInfo entityInfo){
		dao.delete(entityInfo);
	}


	/**
	 * 列表查询
	 * @param entityInfo
	 * @return
	 */
	public Map<String,Object> findSysuserroleByEntity(SysuserroleInfo entityInfo){
		List<SysuserroleInfo> list = dao.selectEntitys(entityInfo);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("data", list);
		return resultMap;
	}
	
	
	public void insertBatch(List<SysuserroleInfo> surList){		
		//先删除人员分配的角色
		SysuserroleInfo surInfo = new SysuserroleInfo();
		surInfo.setEid(surList.get(0).getEid());
		dao.delete(surInfo);
		//新增人员分配的角色
		setEntitiesID(surList);
		dao.batchInsert("",surList);
	}

}

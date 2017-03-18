/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: SysrolemoduleService.java
 * 
 */
package com.syscore.system.sysrolemodule.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syscore.commons.service.BasService;
import com.syscore.system.sysrolemodule.dao.ISysrolemoduleDao;
import com.syscore.system.sysrolemodule.model.SysrolemoduleInfo;
import com.syscore.system.sysrolemodule.service.ISysrolemoduleService;

/**
 * @ClassName : SysrolemoduleService
 * 
 * @Description : 对应—角色模块_SERVICE
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class SysrolemoduleService extends BasService<SysrolemoduleInfo, ISysrolemoduleDao> implements ISysrolemoduleService {
	
	public void insertSysrolemodule(SysrolemoduleInfo info) {
		dao.insert(info);
	}

	public void deleteSysrolemodule(SysrolemoduleInfo info) {
		dao.delete(info);
	}
	
	public Map<String, Object> findSysrolemoduleByEntity(SysrolemoduleInfo entityInfo) {
		List<SysrolemoduleInfo> infoList = dao.selectEntitys(entityInfo);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("data", infoList);
		
		return resultMap;
	}
  	
	@SuppressWarnings("unchecked")
	public void insertBatch(Map<String,Object> paramMap) {
		List<SysrolemoduleInfo>  infoList = (List<SysrolemoduleInfo>)paramMap.get("data");
		dao.batchInsert("SysrolemoduleInfo.insert", infoList);
	}

}

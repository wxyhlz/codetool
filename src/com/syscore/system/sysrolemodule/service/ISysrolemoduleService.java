/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ISysrolemoduleService.java
 * 
 */
package com.syscore.system.sysrolemodule.service;

import java.util.Map;

import com.syscore.commons.service.IBasService;
import com.syscore.system.sysrolemodule.model.SysrolemoduleInfo;

/**
 * @InterName : ISysrolemoduleService
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
public interface ISysrolemoduleService extends IBasService<SysrolemoduleInfo> {

	/**
	 * 增加
	 * @param info
	 */
	public void insertSysrolemodule(SysrolemoduleInfo info);

	/**
	 * 删除
	 * @param info
	 */
	public void deleteSysrolemodule(SysrolemoduleInfo info);

	/**
	 * 数据查询
	 * @param entityInfo
	 * @return
	 */
	public Map<String, Object> findSysrolemoduleByEntity(SysrolemoduleInfo entityInfo); 

	/**
	 * 批量增加
	 * @param paramMap
	 * @return
	 */
	public void insertBatch(Map<String,Object> paramMap);

}

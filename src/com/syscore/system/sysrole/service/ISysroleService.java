/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ISysroleService.java
 * 
 */
package com.syscore.system.sysrole.service;

import java.util.Map;

import com.syscore.commons.service.IBasService;
import com.syscore.system.sysrole.model.SysroleInfo;

/**
 * @InterName : ISysroleService
 * 
 * @Description : 角色_SERVICE
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public interface ISysroleService extends IBasService<SysroleInfo> {
	/**
	 * 增加
	 * @param info
	 */
	public void insertSysrole(SysroleInfo entityInfo);

	/**
	 * 删除
	 * @param info
	 */
	public void deleteSysrole(SysroleInfo entityInfo);

	/**
	 * 修改
	 * @param info
	 */
	public void updateSysrole(SysroleInfo entityInfo); 
	
	/**
	 * 查询
	 * @param entityInfo
	 * @return
	 */
	public SysroleInfo findSysrole(SysroleInfo entityInfo);

	/**
	 * 数据查询
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> findSysroleByMap(Map<String, Object> paramMap);


	/**
	 * 获取所有模块并标志角色已经选中的模块
	 * @param entityInfo
	 * @return
	 */
	public Map<String,Object> findAllModulesAndMarkCheckedByRole(SysroleInfo entityInfo);
	
	/**
	 * 设置角色分配模块
	 * @param paramMap
	 */
	public void updateModulesToRole(Map<String,String> paramMap);

}

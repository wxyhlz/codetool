/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ISysmoduleService.java
 * 
 */
package com.syscore.system.sysmodule.service;

import java.util.Map;

import com.syscore.commons.service.IBasService;
import com.syscore.system.sysmodule.model.SysmoduleInfo;

/**
 * @InterName : ISysmoduleService
 * 
 * @Description : 模块_SERVICE
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public interface ISysmoduleService extends IBasService<SysmoduleInfo> {

	/**
	 * 增加
	 * @param info
	 */
	public void insertModule(SysmoduleInfo entityInfo);

	/**
	 * 删除
	 * @param info
	 */
	public void deleteModule(SysmoduleInfo entityInfo);

	/**
	 * 修改
	 * @param info
	 */
	public void updateModule(SysmoduleInfo entityInfo);

	/**
	 * 分页查询
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> findModuleByPage(Map<String, Object> paramMap);
	
	/**
	 * 数据查询
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> findModuleByMap(Map<String, Object> paramMap);

	/**
	 * 查找用户所能操作的模块
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> findLoginModules(Map<String, Object> paramMap);

}

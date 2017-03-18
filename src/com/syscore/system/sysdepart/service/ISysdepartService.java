/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ISysdepartService.java
 * 
 */
package com.syscore.system.sysdepart.service;

import java.util.Map;

import com.syscore.commons.service.IBasService;
import com.syscore.system.sysdepart.model.SysdepartInfo;

/**
 * @InterName : ISysdepartService
 * 
 * @Description : 部门_SERVICE
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public interface ISysdepartService extends IBasService<SysdepartInfo> {

	/**
	 * 增加
	 * @param info
	 */
	public void insertSysdepart(SysdepartInfo entityInfo);

	/**
	 * 删除
	 * @param info
	 */
	public void deleteSysdepart(SysdepartInfo entityInfo);

	/**
	 * 修改
	 * @param info
	 */
	public void updateSysdepart(SysdepartInfo entityInfo);
	

	/**
	 * 数据查询
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> findSysdepartByMap(Map<String, Object> paramMap);


	/**
	 * 分页查询
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> findSysdepartByPage(Map<String, Object> paramMap);
 

}

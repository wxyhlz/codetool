/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ISysuserroleService.java
 * 
 */
package com.syscore.system.sysuserrole.service;

import java.util.List;
import java.util.Map;

import com.syscore.commons.service.IBasService;
import com.syscore.system.sysuserrole.model.SysuserroleInfo;

/**
 * @InterName : ISysuserroleService
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
public interface ISysuserroleService extends IBasService<SysuserroleInfo> {

	/**
	 * 新增
	 * @param entityInfo
	 */
	public void insertSysuserrole(SysuserroleInfo entityInfo);


	/**
	 * 删除
	 * @param entityInfo
	 */
	public void deleteSysuserrole(SysuserroleInfo entityInfo);

	/**
	 * 列表查询
	 * @param entityInfo
	 * @return
	 */
	public Map<String,Object> findSysuserroleByEntity(SysuserroleInfo entityInfo);
	
	/**
	 * 批量增加
	 * @param surList
	 */
	public void insertBatch(List<SysuserroleInfo> surList);

}

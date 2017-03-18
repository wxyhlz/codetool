/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ISysuserService.java
 * 
 */
package com.syscore.system.sysuser.service;

import java.util.Map;

import com.syscore.commons.service.IBasService;
import com.syscore.system.sysuser.model.SysuserInfo;

/**
 * @InterName : ISysuserService
 * 
 * @Description : 人员_SERVICE
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public interface ISysuserService extends IBasService<SysuserInfo> {
	
	/**
	 * 添加
	 * @param info
	 */
	public void insertSysuser(SysuserInfo info);

	/**
	 * 删除
	 * @param info
	 */
	public void deleteSysuser(SysuserInfo info);

	/**
	 * 修改
	 * @param info
	 */
	public void updateSysuser(SysuserInfo info);

	/**
	 * 分页查询
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> findSysuserByPage(Map<String, Object> paramMap);
	
	/**
	 * 数据查询
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> findSysuserByMap(Map<String, Object> paramMap);

	/**
	 * 判断登录用户
	 * @param entityInfo
	 * @return
	 */
	public SysuserInfo findLogin(SysuserInfo entityInfo);
	
	/**
	 * 获取所有角色并标志人员已经选中的模块
	 * @param entityInfo
	 * @return
	 */
	public Map<String,Object> findAllRoleAndMarkCheckedByUser(SysuserInfo entityInfo);
	
	/**
	 * 设置人员分配角色
	 * @param paramMap
	 */
	public void updateRolesToUser(Map<String,String> paramMap);

}

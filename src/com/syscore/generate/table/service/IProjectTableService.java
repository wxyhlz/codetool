/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: IProjectTableService.java
 * 
 */
package com.syscore.generate.table.service;

import java.util.Map;

import com.syscore.commons.service.IBasService;
import com.syscore.generate.table.model.ProjectTableInfo;

/**
 * @InterName : IProjectTableService
 * 
 * @Description : 关系表和实体类对应关系_SERVICE
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public interface IProjectTableService extends IBasService<ProjectTableInfo> {
 
	/**
	 * 添加
	 * @param info
	 */
	public void insertProjectTable(ProjectTableInfo entityInfo);

	/**
	 * 删除
	 * @param info
	 */
	public void deleteProjectTable(ProjectTableInfo entityInfo);

	/**
	 * 修改
	 * @param info
	 */
	public void updateProjectTable(ProjectTableInfo entityInfo);

	/**
	 * 分页查询
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> findProjectTableByPage(Map<String, Object> paramMap);

}

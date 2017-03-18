/*
 * 版　权: 江苏百年软件 Copyright 2013-2015,All rights reserved
 * 
 * 文件名: IProjectService.java
 * 
 */
package com.syscore.generate.project.service;

import java.util.Map;

import com.syscore.commons.service.IBasService;
import com.syscore.generate.project.model.ProjectInfo;

/**
 * @InterName : IProjectService
 * 
 * @Description : 项目_SERVICE
 * 
 * @author cqf
 * 
 * @date 2014-11-21
 * 
 * @version 1.0
 *
 */
public interface IProjectService extends IBasService<ProjectInfo> {

	/**
	 * 新增
	 * @param info
	 */
	public void insertProject(ProjectInfo entityInfo);

	/**
	 * 删除
	 * @param info
	 */
	public void deleteProject(ProjectInfo entityInfo);

	/**
	 * 修改
	 * @param info
	 */
	public void updateProject(ProjectInfo entityInfo);

	/**
	 * 数据查询
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> findProjectByMap(Map<String, Object> paramMap);
	
	/**
	 * 数据库连接
	 * @param paramMap
	 */
	public void dbconnect(Map<String,Object> paramMap);
	
	/**
	 * 查找项目信息
	 * @param entityInfo
	 * @return
	 */
	public ProjectInfo findProjectInfo(ProjectInfo entityInfo);

}

/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: IProjectauthorService.java
 * 
 */
package com.syscore.generate.author.service;

import java.util.Map;

import com.syscore.commons.service.IBasService;
import com.syscore.generate.author.model.ProjectauthorInfo;

/**
 * @InterName : IProjectauthorService
 * 
 * @Description : 编写者_SERVICE
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public interface IProjectauthorService extends IBasService<ProjectauthorInfo> {
 
	/**
	 * 添加
	 * @param info
	 */
	public void insert(ProjectauthorInfo entityInfo);

	/**
	 * 删除
	 * @param info
	 */
	public void delete(ProjectauthorInfo entityInfo);

	/**
	 * 修改
	 * @param info
	 */
	public void update(ProjectauthorInfo entityInfo);

	/**
	 * 分页查询
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> selectByPage(Map<String, Object> paramMap);
	
	/**
	 * 实体查找
	 * @param entityInfo
	 * @return
	 */
	public ProjectauthorInfo findProjectauthorEntity(ProjectauthorInfo entityInfo);
 

}

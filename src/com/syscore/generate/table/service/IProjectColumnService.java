/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: IProjectColumnService.java
 * 
 */
package com.syscore.generate.table.service;

import java.util.Map;

import com.syscore.commons.service.IBasService;
import com.syscore.generate.table.model.ProjectColumnInfo;

/**
 * @InterName : IProjectColumnService
 * 
 * @Description : 关系表字段和实体类对应关系_SERVICE
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public interface IProjectColumnService extends IBasService<ProjectColumnInfo> {

	/**
	 * 数据查询
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> findProjectColumnByMap(Map<String, Object> paramMap);

	/**
	 * 刷新
	 * @param paramMap
	 * @return
	 */
	public void updateRefresh(Map<String,Object> paramMap);
 
	/**
	 * 删除
	 * @param info
	 */
	public void deleteProjectColumn(ProjectColumnInfo entityInfo);
 
	
	/**
	 * 自动生成功能模块文件
	 * @param paramMap
	 */
	public void updateGenerateFile(Map<String,Object> paramMap);
	

}

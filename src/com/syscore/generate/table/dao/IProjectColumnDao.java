/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: IProjectColumnDao.java
 * 
 */
package com.syscore.generate.table.dao;

import java.util.List;
import java.util.Map;

import com.syscore.commons.dao.IBasDao;
import com.syscore.generate.table.dto.ColumnsDto;
import com.syscore.generate.table.model.ProjectColumnInfo;

/**
 * @InterName : IProjectColumnDao
 * 
 * @Description : 关系表字段和实体类对应关系_DAO
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public interface IProjectColumnDao extends IBasDao<ProjectColumnInfo> {
	
	/**
	 * 获取表属性
	 * @param paramMap
	 * @return
	 */
	public List<ColumnsDto> selectColumns(Map<String,Object> paramMap);

}
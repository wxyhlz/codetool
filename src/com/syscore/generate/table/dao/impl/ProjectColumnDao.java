/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ProjectColumnDao.java
 * 
 */
package com.syscore.generate.table.dao.impl;

import java.util.List;
import java.util.Map;

import com.syscore.commons.dao.BasDao;
import com.syscore.generate.table.dao.IProjectColumnDao;
import com.syscore.generate.table.dto.ColumnsDto;
import com.syscore.generate.table.model.ProjectColumnInfo;

/**
 * @ClassName : ProjectColumnDao
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
public class ProjectColumnDao extends BasDao<ProjectColumnInfo> implements IProjectColumnDao {

	@SuppressWarnings({ "unchecked" })
	public List<ColumnsDto> selectColumns(Map<String, Object> paramMap) {
		return this.getSqlMapClientTemplate().queryForList("ProjectColumnInfo.selectColumns",paramMap);
	}

}

package com.syscore.utils.orm;

import com.syscore.generate.project.model.ProjectInfo;
import com.syscore.generate.table.model.ProjectTableInfo;

/**
 * 抽象ORM
 * @author Administrator
 *
 */
public abstract class AbstractOrm { 
	
	/**
	 * 获取表的SQL
	 * @param pInfo
	 * @param ptInfo
	 * @return
	 */
	
	public abstract String getTableSQL(ProjectInfo pInfo, ProjectTableInfo ptInfo);
	
	/**
	 * 获取列的SQL
	 * @param pInfo
	 * @return
	 */
	public abstract  String getColumnSQL(ProjectInfo pInfo, ProjectTableInfo ptInfo);

}

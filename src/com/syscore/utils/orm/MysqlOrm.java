package com.syscore.utils.orm;

import com.syscore.generate.project.model.ProjectInfo;
import com.syscore.generate.table.model.ProjectTableInfo;

public class MysqlOrm extends AbstractOrm {

	@Override
	public String getColumnSQL(ProjectInfo pInfo, ProjectTableInfo ptInfo) {
		String SQL = " select  column_name, data_type, column_comment"
			+" from information_schema.columns" 
			+" where table_schema = '"+pInfo.getDbname()+"'"  
			+" and table_name = '"+ptInfo.getTblname()+"'";
		return SQL;
	}

	@Override
	public String getTableSQL(ProjectInfo pInfo, ProjectTableInfo ptInfo) {
		String SQL = "select table_comment from information_schema.tables"
			+" where table_schema = '"+pInfo.getDbname()+"' and table_name = '"+ptInfo.getTblname()+"'";
		return SQL;
	}
 
	
}

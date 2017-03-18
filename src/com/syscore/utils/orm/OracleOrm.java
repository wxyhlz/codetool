package com.syscore.utils.orm;

import com.syscore.generate.project.model.ProjectInfo;
import com.syscore.generate.table.model.ProjectTableInfo;

public class OracleOrm extends AbstractOrm {

	@Override
	public String getColumnSQL(ProjectInfo pInfo, ProjectTableInfo ptInfo) {
		String SQL = " select t.column_name as  column_name,t.data_type as data_type,a.comments as column_comment"
			+" from all_tab_columns t, user_col_comments a"
			+" where t.table_name = '"+ptInfo.getTblname().toUpperCase()+"'"
			+" and t.table_name = a.table_name"
			+" and t.column_name = a.column_name"
			+" and t.OWNER = '"+pInfo.getOwner().toUpperCase()+"'";
		return SQL;
	}

	@Override
	public String getTableSQL(ProjectInfo pInfo, ProjectTableInfo ptInfo) {
		String SQL = "select t.comments as table_comment from user_tab_comments t where t.table_name='"+ptInfo.getTblname().toUpperCase()+"'";
		return SQL;
	}
 
}

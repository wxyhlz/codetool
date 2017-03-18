package com.syscore.utils;

import com.syscore.generate.project.model.ProjectInfo;
import com.syscore.generate.table.model.ProjectTableInfo;
import com.syscore.utils.jdbc.Dbconn;

public class TestConn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ProjectTableInfo ptInfo = new ProjectTableInfo();
		ptInfo.setTblname("s_sysdepart");
		
		ProjectInfo pInfo = new ProjectInfo();
		pInfo.setDbname("dotblue");
		pInfo.setDbtype("mysql");
		pInfo.setIpport("192.168.1.105:3306");
		pInfo.setDbpassword("root");
		pInfo.setDbuser("root");
		System.out.println(Dbconn.getTableComment(pInfo, ptInfo));

	}

}

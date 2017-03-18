package com.syscore.utils.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.syscore.commons.except.BusinessException;
import com.syscore.generate.project.model.ProjectInfo;
import com.syscore.generate.table.dto.ColumnsDto;
import com.syscore.generate.table.model.ProjectTableInfo;
import com.syscore.utils.orm.AbstractOrm;
import com.syscore.utils.orm.MysqlOrm;
import com.syscore.utils.orm.OracleOrm;

public class Dbconn {
	
	private static Connection conn(ProjectInfo info) {
		Connection conn = null;
		try {
			String driver = "";
			String url = "";
			if("oracle".equals(info.getDbtype())){
				driver = "oracle.jdbc.driver.OracleDriver";
				url = "jdbc:oracle:thin:@"+info.getIpport()+":"+info.getDbname();	// @localhost为服务器名，sjzwish为数据库实例名
			}else if("mysql".equals(info.getDbtype())){
				driver = "com.mysql.jdbc.Driver";
				url = "jdbc:mysql://"+info.getIpport()+"/"+info.getDbname()+"?useUnicode=true&amp;characterEncoding=utf-8";
			}
			Class.forName(driver);	// 实例化oracle数据库驱动程序(建立中间件)				
			conn = DriverManager.getConnection(url, info.getDbuser(), info.getDbpassword());	// 连接数据库，a代表帐户,a代表密码

		} catch (Exception e) {
			throw new BusinessException("数据库连接失败："+e.getMessage());
		}

		return conn;
	}
	
	/**
	 * 判断是否连接
	 * @param info
	 */	
	public static void dbconnLink(ProjectInfo info){
		Connection conn = conn(info);		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	} 
	
	/**
	 * 获取表的注释
	 * @param pInfo
	 * @return
	 */
	public static String getTableComment(ProjectInfo pInfo, ProjectTableInfo ptInfo){
		Connection conn = conn(pInfo);		
		if(conn!=null){
			AbstractOrm ao = null;
			if("mysql".equals(pInfo.getDbtype())){
				ao = new MysqlOrm();
			}else if("oracle".equals(pInfo.getDbtype())){
				ao = new OracleOrm();
			}
			
			Statement stmt = null;
			ResultSet rs = null;			
			try {
				stmt = conn.createStatement();
	
				//判断表是否存在
				String SQL = ao.getTableSQL(pInfo, ptInfo);
				rs = stmt.executeQuery(SQL);
				String comment = "not table";
				while (rs.next()) {// 使当前记录指针定位到记录集的第一条记录
					comment = rs.getString("table_comment"); 
				} 	
				return comment;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					// 关闭数据库，结束进程
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return "conn database error。";		
	}
	
	/**
	 * 获取表列
	 * @param info
	 * @return
	 */
	public static List<ColumnsDto> getTableColumn(ProjectInfo pInfo, ProjectTableInfo ptInfo){
		Connection conn = conn(pInfo);		
		if(conn!=null){
			AbstractOrm ao = null;
			if("mysql".equals(pInfo.getDbtype())){
				ao = new MysqlOrm();
			}else if("oracle".equals(pInfo.getDbtype())){
				ao = new OracleOrm();
			}
			
			Statement stmt = null;
			ResultSet rs = null;			
			try {
				stmt = conn.createStatement();	
				//获取表的所有列
				String SQL = ao.getColumnSQL(pInfo, ptInfo);
				rs = stmt.executeQuery(SQL);
				
				String colname = "", dttype = "", colcomment = "";
				List<ColumnsDto> colmnList = new ArrayList<ColumnsDto>();				
				while (rs.next()) {// 使当前记录指针定位到记录集的第一条记录
					ColumnsDto cd = new ColumnsDto(); 
					colname = rs.getString("column_name");
					dttype = rs.getString("data_type");
					colcomment = rs.getString("column_comment");
					
					cd.setColumn_comment(colcomment);
					cd.setColumn_name(colname);
					cd.setData_type(dttype);
					colmnList.add(cd);
				} 	
				return colmnList;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					// 关闭数据库，结束进程
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;	
	}
	 

}

/**   
 * @Package	: com.dotblue.generate.dto 
 * @Title	: ColumnsDto.java 
 * @date 	: 2013-12-12 下午10:46:00 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.generate.table.dto;

import java.io.Serializable;

/** 
 * @Description	:  表的数据列Dto
 * @ClassName: ColumnsDto 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public class ColumnsDto implements Serializable {

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */
	
	private static final long serialVersionUID = 2838695171234100979L;
	
	/**
	 * 列名
	 */
	private String column_name;
	
	/**
	 * 列类型
	 */
	private String data_type;
	
	/**
	 * 列注释
	 */
	private String column_comment;

	/**
	 * @return the column_name
	 */
	public String getColumn_name() {
		return column_name;
	}

	/**
	 * @param columnName the column_name to set
	 */
	public void setColumn_name(String columnName) {
		column_name = columnName;
	}

	/**
	 * @return the data_type
	 */
	public String getData_type() {
		return data_type;
	}

	/**
	 * @param dataType the data_type to set
	 */
	public void setData_type(String dataType) {
		data_type = dataType;
	}

	/**
	 * @return the column_comment
	 */
	public String getColumn_comment() {
		return column_comment;
	}

	/**
	 * @param columnComment the column_comment to set
	 */
	public void setColumn_comment(String columnComment) {
		column_comment = columnComment;
	}
	
}

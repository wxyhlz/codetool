/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ProjectColumnInfo.java
 * 
 */
package com.syscore.generate.table.model;

import com.syscore.commons.model.BasInfo;


/**
 * @ClassName : ProjectColumnInfo
 * 
 * @Description : 关系表字段和实体类对应关系_INFO
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class ProjectColumnInfo extends BasInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 排序
	 */
	private Integer ordernum;

	/**
	 * 主表id
	 */
	private String pid;

	/**
	 * 列名
	 */
	private String colname;
	
	/**
	 * Java属性
	 */
	private String javacolname;

	/**
	 * 列注释
	 */
	private String colcomment;

	/**
	 * 列类型
	 */
	private String coltype;

	/**
	 * JAVA类型
	 */
	private String javatype;


	/**
	 * @return the ordernum
	 */
	public Integer getOrdernum() {
		return ordernum;
	}

	/**
	 * @param ordernum the ordernum to set
	 */
	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}

	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	/**
	 * @return the colname
	 */
	public String getColname() {
		return colname;
	}

	/**
	 * @param colname the colname to set
	 */
	public void setColname(String colname) {
		this.colname = colname;
	}

	/**
	 * @return the colcomment
	 */
	public String getColcomment() {
		return colcomment;
	}

	/**
	 * @param colcomment the colcomment to set
	 */
	public void setColcomment(String colcomment) {
		this.colcomment = colcomment;
	}

	/**
	 * @return the coltype
	 */
	public String getColtype() {
		return coltype;
	}

	/**
	 * @param coltype the coltype to set
	 */
	public void setColtype(String coltype) {
		this.coltype = coltype;
	}

	/**
	 * @return the javatype
	 */
	public String getJavatype() {
		return javatype;
	}

	/**
	 * @param javatype the javatype to set
	 */
	public void setJavatype(String javatype) {
		this.javatype = javatype;
	}

	public String getJavacolname() {
		return javacolname;
	}

	public void setJavacolname(String javacolname) {
		this.javacolname = javacolname;
	}
	
	
}

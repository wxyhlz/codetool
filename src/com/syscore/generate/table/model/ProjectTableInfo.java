/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ProjectTableInfo.java
 * 
 */
package com.syscore.generate.table.model;

import com.syscore.commons.model.BasInfo;


/**
 * @ClassName : ProjectTableInfo
 * 
 * @Description : 关系表和实体类对应关系_INFO
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class ProjectTableInfo extends BasInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 所属项目id
	 */
	private String prjid;
	
	//所属项目名称
	private String prjname;

	/**
	 * 表名
	 */
	private String tblname;

	/**
	 * 表注释
	 */
	private String tblcomment;

	/**
	 * 包
	 */
	private String pkgmodelpath;

	/**
	 * 实体类
	 */
	private String entityclass;

	/**
	 * 作者
	 */
	private String author;

	/**
	 * 登记时间
	 */
	private String regtime;
	
	public String getPrjname() {
		return prjname;
	}

	public void setPrjname(String prjname) {
		this.prjname = prjname;
	}

	public String getTblname() {
		return tblname;
	}

	public void setTblname(String tblname) {
		this.tblname = tblname;
	}

	public String getTblcomment() {
		return tblcomment;
	}

	public void setTblcomment(String tblcomment) {
		this.tblcomment = tblcomment;
	}

	public String getPkgmodelpath() {
		return pkgmodelpath;
	}

	public void setPkgmodelpath(String pkgmodelpath) {
		this.pkgmodelpath = pkgmodelpath;
	}

	public String getEntityclass() {
		return entityclass;
	}

	public void setEntityclass(String entityclass) {
		this.entityclass = entityclass;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public String getPrjid() {
		return prjid;
	}

	public void setPrjid(String prjid) {
		this.prjid = prjid;
	}
	
	

}

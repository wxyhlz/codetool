/*
 * 版　权: 江苏百年软件 Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ProjectInfo.java
 * 
 */
package com.syscore.generate.project.model;

import com.syscore.commons.model.BasInfo;


/**
 * @ClassName : ProjectInfo
 * 
 * @Description : 项目_INFO
 * 
 * @author cqf
 * 
 * @date 2014-11-21
 * 
 * @version 1.0
 *
 */
public class ProjectInfo extends BasInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 项目编码
	 */
	private String code;

	/**
	 * 项目名称
	 */
	private String name;

	/**
	 * 数据类型
	 */
	private String dbtype;
	
	/**
	 * 地址和端口
	 */
	private String ipport;	

	/**
	 * 数据库名
	 */
	private String dbname;

	/**
	 * 数据库用户
	 */
	private String dbuser;

	/**
	 * 数据库密码
	 */
	private String dbpassword; 
	
	/**
	 * 所有者
	 */
	private String owner;
	
	/**
	 * 登记时间
	 */
	private String regtime;

	/**
	 * 有效标记
	 */
	private String valid;


	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the dbname
	 */
	public String getDbname() {
		return dbname;
	}

	/**
	 * @param dbname the dbname to set
	 */
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	/**
	 * @return the dbtype
	 */
	public String getDbtype() {
		return dbtype;
	}

	/**
	 * @param dbtype the dbtype to set
	 */
	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}

	/**
	 * @return the dbuser
	 */
	public String getDbuser() {
		return dbuser;
	}

	/**
	 * @param dbuser the dbuser to set
	 */
	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}

	/**
	 * @return the dbpassword
	 */
	public String getDbpassword() {
		return dbpassword;
	}

	/**
	 * @param dbpassword the dbpassword to set
	 */
	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}

	/**
	 * @return the valid
	 */
	public String getValid() {
		return valid;
	}

	/**
	 * @param valid the valid to set
	 */
	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getIpport() {
		return ipport;
	}

	public void setIpport(String ipport) {
		this.ipport = ipport;
	}
	
	
}

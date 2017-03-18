/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: SysuserInfo.java
 * 
 */
package com.syscore.system.sysuser.model;

import com.syscore.commons.model.BasInfo;
import com.syscore.system.sysdepart.model.SysdepartInfo;



/**
 * @ClassName : SysuserInfo
 * 
 * @Description : 人员_INFO
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class SysuserInfo extends BasInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 部门
	 */
	private String dpid;

	/**
	 * 部门层结构
	 */
	private String dpids;

	/**
	 * 工号
	 */
	private String code;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 账号路径
	 */
	private String userpath;

	/**
	 * 默认 1；0 无效 ；1 有效
	 */
	private String valid;
	
	/**
	 * 所属部门
	 */
	private SysdepartInfo depart;

	public String getDpid() {
		return dpid;
	}

	public void setDpid(String dpid) {
		this.dpid = dpid;
	}

	public String getDpids() {
		return dpids;
	}

	public void setDpids(String dpids) {
		this.dpids = dpids;
	}

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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userpath
	 */
	public String getUserpath() {
		return userpath;
	}

	/**
	 * @param userpath the userpath to set
	 */
	public void setUserpath(String userpath) {
		this.userpath = userpath;
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

	public SysdepartInfo getDepart() {
		return depart;
	}

	public void setDepart(SysdepartInfo depart) {
		this.depart = depart;
	}
		
}

/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: ProjectauthorInfo.java
 * 
 */
package com.syscore.generate.author.model;

import com.syscore.commons.model.BasInfo;


/**
 * @ClassName : ProjectauthorInfo
 * 
 * @Description : 编写者_INFO
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class ProjectauthorInfo extends BasInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 项目ID
	 */
	private String prjid;
	
	//项目名称
	private String prjname;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 文件路径
	 */
	private String filepath;

	/**
	 * 
	 */
	private String regtime;


	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * @return the regtime
	 */
	public String getRegtime() {
		return regtime;
	}

	/**
	 * @param regtime the regtime to set
	 */
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public String getPrjid() {
		return prjid;
	}

	public void setPrjid(String prjid) {
		this.prjid = prjid;
	}

	public String getPrjname() {
		return prjname;
	}

	public void setPrjname(String prjname) {
		this.prjname = prjname;
	}
	
	
}

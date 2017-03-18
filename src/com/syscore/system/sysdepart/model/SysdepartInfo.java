/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: SysdepartInfo.java
 * 
 */
package com.syscore.system.sysdepart.model;

import com.syscore.commons.model.BasInfo;



/**
 * @ClassName : SysdepartInfo
 * 
 * @Description : 部门_INFO
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class SysdepartInfo extends BasInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 父ID
	 */
	private String pid;

	/**
	 * 编码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 结构层
	 */
	private String pids;

	/**
	 * 默认 1；0 无效 ；1 有效
	 */
	private String lastnode;

	/**
	 * 默认 1；0 无效 ；1 有效
	 */
	private String valid;
	
	/**
	 * 父节点ID
	 */
	private String _parentId;
	
	/**
	 * 打开状态
	 */
	private String state = "closed";


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
	 * @return the pids
	 */
	public String getPids() {
		return pids;
	}

	/**
	 * @param pids the pids to set
	 */
	public void setPids(String pids) {
		this.pids = pids;
	}

	/**
	 * @return the lastnode
	 */
	public String getLastnode() {
		return lastnode;
	}

	/**
	 * @param lastnode the lastnode to set
	 */
	public void setLastnode(String lastnode) {
		this.lastnode = lastnode;
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

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String parentId) {
		_parentId = parentId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}

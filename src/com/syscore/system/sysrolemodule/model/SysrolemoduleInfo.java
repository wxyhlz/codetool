/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: SysrolemoduleInfo.java
 * 
 */
package com.syscore.system.sysrolemodule.model;

import com.syscore.commons.model.BasInfo;


/**
 * @ClassName : SysrolemoduleInfo
 * 
 * @Description : 对应—角色模块_INFO
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class SysrolemoduleInfo extends BasInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	private String rid;

	/**
	 * 模块ID
	 */
	private String mid;


	/**
	 * @return the rid
	 */
	public String getRid() {
		return rid;
	}

	/**
	 * @param rid the rid to set
	 */
	public void setRid(String rid) {
		this.rid = rid;
	}

	/**
	 * @return the mid
	 */
	public String getMid() {
		return mid;
	}

	/**
	 * @param mid the mid to set
	 */
	public void setMid(String mid) {
		this.mid = mid;
	}
}

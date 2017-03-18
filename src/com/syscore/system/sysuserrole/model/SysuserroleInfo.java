/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: SysuserroleInfo.java
 * 
 */
package com.syscore.system.sysuserrole.model;

import com.syscore.commons.model.BasInfo;



/**
 * @ClassName : SysuserroleInfo
 * 
 * @Description : 对应—人员角色_INFO
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class SysuserroleInfo extends BasInfo {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 人员ID
	 */
	private String eid;

	/**
	 * 角色ID
	 */
	private String rid;

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

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
}

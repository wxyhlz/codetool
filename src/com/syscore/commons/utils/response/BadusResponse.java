/********************************
 *  
 * @Copyright (c) by  
 * @Create Author: Tide
 * @Create Date: Jun 28, 2011 
 * @Modify Date:
 * @Version: V 1.0 
 */ 

package com.syscore.commons.utils.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 主要用于Action返回前台数据信息
 * @author Administrator
 *
 */
public class BadusResponse {
	//记录行为的结果
	private boolean successed; 
	//记录行为的信息
	private List<String> errors = new ArrayList<String>();
	//记录行为获取的数据
	private Map<String,Object> dataMap = new HashMap<String,Object>();

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public boolean isSuccessed() {
		return successed;
	}

	public void setSuccessed(boolean successed) {
		this.successed = successed;
	}

	/**
	 * @return the errors
	 */
	public List<String> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List<String> errors) {
		this.errors = errors;
	} 

	
 
}

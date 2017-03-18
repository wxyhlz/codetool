/********************************
 * @Copyright (c) by CUIQINGFENG 
 * @Version: V 1.0 
 */  

package com.syscore.commons.model;

import java.io.Serializable;

/** 
 * @ClassName:  BasStrInfo
 * 
 * @Description: 主键类型为_String
 *  
 * @author: tide
 *  
 * @date: 2013-02-27 
 *  
 */

public class BasInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id; 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	} 

}

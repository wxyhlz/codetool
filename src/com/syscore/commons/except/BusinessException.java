/********************************
 *  
 * @Copyright (c) by  
 * @Create Author: Tide
 * @Create Date: Jun 28, 2011 
 * @Modify Date:
 * @Version: V 1.0 
 */ 

package com.syscore.commons.except;

import com.syscore.commons.except.adapter.ExceptionAdapter;


public class BusinessException extends ExceptionAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BusinessException(Exception e) {
		super(e);
	}

	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
	} 
	
}

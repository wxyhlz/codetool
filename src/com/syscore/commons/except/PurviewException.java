/********************************
 *  
 * @Copyright (c) by  
 * @Create Author: Tide
 * @Create Date: Jun 28, 2011 
 * @Modify Date:
 * @Version: V 1.0 
 */ 

package com.syscore.commons.except;

public class PurviewException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//因为业务无需用到堆栈信息，所以把堆栈信息屏蔽以提高效率
	@Override
	public synchronized Throwable fillInStackTrace() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

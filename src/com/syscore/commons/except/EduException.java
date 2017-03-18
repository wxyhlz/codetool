package com.syscore.commons.except;

public class EduException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EduException(){
		super();
	}
	
	public EduException(String msg) {
		super(msg);
	}

	public EduException(String msg, Throwable cause) {
		super(msg, cause);
	} 
}

package com.syscore.commons.except.adapter;


public abstract class ExceptionAdapter extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExceptionAdapter() {
		super();
	}
	
	public ExceptionAdapter(Exception e) {
		super(e);
	}

	public ExceptionAdapter(String msg) {
		super(msg);
	}
	
	public ExceptionAdapter(String msg, Throwable cause) {
		super(msg, cause);
	}

	 
	public String getMessage() {
		return super.getMessage();
	}

	public Throwable getCause() {
		return super.getCause();
	}

	public void printStackTrace() {
		super.printStackTrace();
	}

	public String toString() {
		return super.toString();
	}

	 
	
	

}

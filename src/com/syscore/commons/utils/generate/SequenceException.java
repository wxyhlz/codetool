package com.syscore.commons.utils.generate;

/**
 * SequenceException
 * 
 * 
 * 
 * @see RuntimeException
 */
public class SequenceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SequenceException() {
		super("生成序列号异常!");
	}

	public SequenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public SequenceException(String message) {
		super(message);
	}

	public SequenceException(Throwable cause) {
		super(cause);
	}

}

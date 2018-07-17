package com.paladin.framework.core.exception;

/**
 * 请求参数异常
 * @author TontZhou
 *
 */
public class BadRequestException extends RollbackException{

	private static final long serialVersionUID = -8265596521676533679L;
	
	
	public BadRequestException() {
		super();
	}

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadRequestException(Throwable cause) {
		super(cause);
	}

}

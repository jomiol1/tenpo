package com.tenpo.api.handler.exception;

public class GeneralException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer code;
	
	public GeneralException(Integer code, String message) {
        super(message);
        this.code = code;
	}

	public Integer getCode() {
		return code;
	}
	
	
}

package com.common.dao.exception;

public class DBException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DBException(String message,Exception e){
		super(message,e);
	}
	public DBException(String message){
		super(message);
	}
    public DBException() {
        super();
    }
    public DBException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
    public DBException(Throwable cause) {
        super(cause);
    }
	
}

package com.dariosantos.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException() {
	
	}
	
	public DataIntegrityException(String msg) {
		super(msg);
		//System.out.println("\n\n--------------ggg---------------------passou aqui--------------\n\n");
	}
	
	public DataIntegrityException(Throwable cause) {
		super(cause);
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	
}

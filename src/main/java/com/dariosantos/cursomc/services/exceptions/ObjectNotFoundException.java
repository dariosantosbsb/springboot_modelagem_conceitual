package com.dariosantos.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException() {
	
	}
	
	public ObjectNotFoundException(String msg) {
		super(msg);
		//System.out.println("\n\n--------------ggg---------------------passou aqui--------------\n\n");
	}
	
	public ObjectNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	
}

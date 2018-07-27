package com.dariosantos.cursomc.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import com.dariosantos.cursomc.services.exceptions.ObjectNotFoundException;


@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)//indicando que é tratador de execao da classe ObjectNotFoundException
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e){
		
		System.out.println("\n\n\n\n\n\n\n ################################### 123123123132 \n\n\n\n\n\n\n\n");
		
		System.out.println("\n\n-----------------------------------passou aqui--------------\n\n");
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		System.out.println(err);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	} 
	
}

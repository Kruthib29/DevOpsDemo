package com.dcl.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class AppExceptionHandler {
	@ExceptionHandler(exception=AppException.class)
public ResponseEntity<?> handleAppException(AppException exception){
	return new ResponseEntity<>(exception.getMessage(),exception.getHttpStatus());
}
	
//	public ResponseEntity<?> handleException(Exception exception){
//		Exception except=new Exception();
//		return new ResponseEntity<>(except.getMessage(),except.getHttpStatus());
//	}
}

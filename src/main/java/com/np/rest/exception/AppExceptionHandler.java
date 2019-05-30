package com.np.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {	
	
	@ResponseBody
	public ResponseEntity<?> handleException(ValidationExceptionHandler exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMsg());
	}

}

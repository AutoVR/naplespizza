package com.np.rest.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.np.rest.exception.resourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.np.NaplespizzaApplication;



@ControllerAdvice
public class exceptionHandler {
	 /** Provides handling of exceptions for the REST service */
	private static final Logger LOGGER = LoggerFactory.getLogger(exceptionHandler.class);
	
	@ExceptionHandler(resourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<errorResponse> handleUserNotFoundException(final resourceNotFoundException ex) {
		LOGGER.error("resource not found error thrown", ex.getMessage());
        return new ResponseEntity<errorResponse>(errorResponse("NOT_FOUND", ex.getResourceId().toString()+ " " + ex.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<errorResponse> handleThrowable(final Throwable ex) {
    	LOGGER.error("Unexpected error", ex);
        return new ResponseEntity<errorResponse>( errorResponse("INTERNAL_SERVER_ERROR", "An unexpected internal server error occured"),HttpStatus.NOT_FOUND);
    }

	private errorResponse errorResponse(String code, String msg) {
		errorResponse response = new errorResponse();
        response.setCode(code);
        response.setMessage(msg);
		return response;
	}

}

package com.np.rest.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import lombok.Data;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Data
public class resourceNotFoundException extends RuntimeException {

	private Long resourceId;
	
	public resourceNotFoundException(Long resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }

	public resourceNotFoundException(Integer id, String message) {
		 super(message);
	        this.resourceId = id.longValue();
	}
}

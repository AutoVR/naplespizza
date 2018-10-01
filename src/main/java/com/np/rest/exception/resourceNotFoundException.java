package com.np.rest.exception;

import lombok.Data;

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

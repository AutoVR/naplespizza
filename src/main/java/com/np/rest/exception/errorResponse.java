package com.np.rest.exception;

import lombok.Data;

@Data
public class errorResponse {
	private  String code;
    private String message;
}

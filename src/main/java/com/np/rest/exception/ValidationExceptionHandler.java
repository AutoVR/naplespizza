package com.np.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ValidationExceptionHandler extends RuntimeException {


		private static final long serialVersionUID = 1L;
		private String msg;

		public ValidationExceptionHandler(String msg) {
			super(msg);
			this.msg = msg;

		}

		public String getMsg() {
			return msg;
		}

}

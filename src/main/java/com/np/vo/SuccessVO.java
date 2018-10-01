package com.np.vo;

import lombok.Data;

@Data
public class SuccessVO {
	private String msg;

	public SuccessVO(String msg) {
		super();
		this.msg = msg;
	}
	
	
}

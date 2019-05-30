package com.np.util;

import java.util.ArrayList;

import org.dozer.DozerConverter;

public class DozerArrayConverter extends DozerConverter<String, String[]> {

	public DozerArrayConverter() {
		super(String.class, String[].class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] convertTo(String source, String[] destination) {
		return source.split(",");
	}

	@Override
	public String convertFrom(String[] source, String destination) {
		return String.join(",",source);
	}

	   
}

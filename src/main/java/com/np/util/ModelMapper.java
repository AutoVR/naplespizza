package com.np.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {
	@Autowired
	private  ModelMapper mapper;
	 
//    private static ModelMapper getMapper() {
//        if (MAPPER == null) {
//            MAPPER = new ModelMapper();
//        }
// 
//        return MAPPER;
//    }
 
    
    
    public <T> T map(Object from, Class<T> toClass) {
		return this.mapper.map(from, toClass);
	}
	
 
 }

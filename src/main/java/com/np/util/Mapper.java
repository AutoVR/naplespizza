package com.np.util;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	
	public <F,T> List<T> mapList(List<F> fromList, final Class<T> toClass) {
	    return fromList.stream()
	            .map(from -> this.dozerBeanMapper.map(from, toClass))
	            .collect(Collectors.toList());
	}
	
	public <T> T map(Object from, Class<T> toClass) {
		return this.dozerBeanMapper.map(from, toClass);
	}
	
}

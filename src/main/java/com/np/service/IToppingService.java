package com.np.service;

import java.util.List;

import com.np.vo.PizzaVO;
import com.np.vo.ToppingVO;

public interface IToppingService {
	public List<ToppingVO> getAllToppings();
	public ToppingVO getToppingbyId(String Id);
	public Boolean addTopping(ToppingVO topping);

}

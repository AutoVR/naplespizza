package com.np.service;

import java.util.List;

import com.np.entity.Topping;
import com.np.vo.PizzaVO;
import com.np.vo.ToppingVO;

public interface IToppingService {
	public List<ToppingVO> getAllToppings();
	public ToppingVO getToppingbyId(String Id);
	public Boolean addTopping(ToppingVO topping);
	public Boolean updateToppings(List<Topping> toppings);
	public List<Topping> getToppingbyIds(List<String> ids);
}

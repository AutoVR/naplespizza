package com.np.service;

import java.util.List;


import com.np.vo.PizzaVO;

public interface IPizzaService {
	public List<PizzaVO> getAllPizzas();
	public PizzaVO getPizzabyId(Integer Id);
	public Boolean addPizza(PizzaVO pizzaVO);

}

package com.np.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.np.entity.Pizza;
import com.np.repository.PizzaRepository;
import com.np.rest.exception.resourceNotFoundException;
import com.np.util.Mapper;
import com.np.vo.PizzaVO;

@Service
public class PizzaService implements IPizzaService {

	@Autowired
	PizzaRepository pizzaRepository;
	
	@Autowired
	private Mapper mapper;
	
	
	@Override
	public List<PizzaVO> getAllPizzas() {
		List<Pizza> pizzas= pizzaRepository.findAll();
		return mapper.mapList(pizzas, PizzaVO.class);
	}

	@Override
	public PizzaVO getPizzabyId(Integer id) {
		Pizza pizza = pizzaRepository.findById(id).orElseThrow(() -> new resourceNotFoundException(id,"Pizza Id not found"));
		
		return mapper.map(pizza, PizzaVO.class);
	}

	@Override
	public Boolean addPizza(PizzaVO pizzaVO) {
		Pizza  pizza = mapper.map(pizzaVO, Pizza.class);
		pizzaRepository.save(pizza);
		return true;
	}

}

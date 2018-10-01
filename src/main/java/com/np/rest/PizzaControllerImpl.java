package com.np.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.np.entity.Order;
import com.np.repository.PizzaRepository;
import com.np.service.IPizzaService;
import com.np.vo.PizzaVO;
import com.np.vo.SuccessVO;
@Component
public class PizzaControllerImpl implements IPizzaController {
	
	@Autowired
	IPizzaService pizzaService;

	@Override
	public ResponseEntity<List<PizzaVO>> getPizzas() {
		List<PizzaVO> pizzas =pizzaService.getAllPizzas();
		return new ResponseEntity<List<PizzaVO>>(pizzas, !pizzas.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<PizzaVO> getPizzaById(@PathVariable("Id")  Integer Id) {
		PizzaVO pizza =pizzaService.getPizzabyId(Id);
		return new ResponseEntity<PizzaVO>(pizza,pizza!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity addPizza(@RequestBody PizzaVO pizzaVO) {
		pizzaService.addPizza(pizzaVO);
		return  new ResponseEntity(new SuccessVO("Pizza created Successfully"), HttpStatus.OK);
	}

}

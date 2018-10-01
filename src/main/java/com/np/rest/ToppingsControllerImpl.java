package com.np.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.np.service.IToppingService;
import com.np.vo.ToppingVO;

@Component
public class ToppingsControllerImpl implements IToppingsController {
	
	@Autowired
	IToppingService toppingService;


	@Override
	public ResponseEntity<List<ToppingVO>> getToppings() {
		List<ToppingVO> toppings =toppingService.getAllToppings();
		return new ResponseEntity<List<ToppingVO>>(toppings, !toppings.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<ToppingVO> getToppingById(String Id) {
		ToppingVO topping =toppingService.getToppingbyId(Id);
		return new ResponseEntity<ToppingVO>(topping,topping!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity addTopping(ToppingVO topping) {
		toppingService.addTopping(topping);
		return  new ResponseEntity(true, HttpStatus.OK);
	}

}

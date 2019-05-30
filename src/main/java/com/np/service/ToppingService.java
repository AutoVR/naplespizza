package com.np.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.np.entity.Pizza;
import com.np.entity.Topping;
import com.np.repository.ToppingRepository;
import com.np.util.Mapper;
import com.np.vo.PizzaVO;
import com.np.vo.ToppingVO;
@Service
public class ToppingService implements IToppingService {
	
	@Autowired
	ToppingRepository toppingRepository;
	
	@Autowired
	private Mapper mapper;
	

	@Override
	public List<ToppingVO> getAllToppings() {
		List<Topping> topping= toppingRepository.findAll();
		return mapper.mapList(topping, ToppingVO.class);
	}

	@Override
	public ToppingVO getToppingbyId(String Id) {
		Optional<Topping> topping = toppingRepository.findById(Id);
		
		return mapper.map(topping, ToppingVO.class);
	}

	@Override
	public Boolean addTopping(ToppingVO toppingVO) {
		Topping  topping = mapper.map(toppingVO, Topping.class);
		toppingRepository.save(topping);
		return null;
	}

	@Override
	public Boolean updateToppings(List<Topping> toppings) {
		toppingRepository.saveAll(toppings);
		return true;
	}

	@Override
	public List<Topping> getToppingbyIds(List<String> ids) {
		return toppingRepository.findAllById(ids);
		
	}

}

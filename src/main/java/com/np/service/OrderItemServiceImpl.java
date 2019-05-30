package com.np.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.np.entity.Customer;
import com.np.entity.ItemToppingId;
import com.np.entity.Order;
import com.np.entity.OrderItem;
import com.np.entity.Pizza;
import com.np.entity.Topping;
import com.np.repository.OrderItemRepository;
import com.np.repository.OrderRepository;
import com.np.repository.PizzaRepository;
import com.np.repository.ToppingRepository;
import com.np.rest.exception.resourceNotFoundException;
import com.np.util.Mapper;
import com.np.util.ModelMapper;
import com.np.vo.OrderItemVO;
import com.np.vo.OrderVO;
import com.np.vo.PizzaVO;
import com.np.vo.ToppingVO;
@Service
public class OrderItemServiceImpl implements IOrderItemService {
	
	
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	@Autowired
	private Mapper mapper;
	
	
	@Override
	public List<OrderItemVO> getAllOrderItem(Long orderId) {
		List<OrderItem> orderItems = orderItemRepository.findByOrderOrderId(orderId);
		return mapper.mapList(orderItems, OrderItemVO.class);
	}

	@Override
	public OrderItemVO getOrderItemByItemId(Long orderId, Long orderItemId) {
		OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(() -> new resourceNotFoundException(orderItemId,"Pizza Id not found"));
		return mapper.map(orderItem, OrderItemVO.class);
	}

	@Override
	public Boolean addOrderItem(Order order, OrderItemVO orderItemVO) throws resourceNotFoundException {
		
		OrderItem  orderItem = mapper.map(orderItemVO, OrderItem.class);
		//String toppings = String.join(",",orderItemVO.getToppings());
		//OrderItem  orderItem = modelMapper.map(orderItemVO, OrderItem.class);
		
		orderItem.setOrder(order);
		
		Pizza pizza =pizzaRepository.findById(orderItemVO.getPizza().getPizzaId()).orElseThrow(() -> new resourceNotFoundException(orderItemVO.getPizza().getPizzaId(),"pizza Id not found"));
		orderItem.setPizza(pizza);
		orderItem.setPrice(pizza.getPrice().add(getToppingsPrice(orderItemVO.getToppings())));
		orderItem.setTopping(orderItem.getTopping());
		
		OrderItem newOrderItem = orderItemRepository.save(orderItem);
		
		return true;
	}
	
	private OrderItemVO mapToOrderItemVO(OrderItem orderItem) {
		OrderItemVO orderItemVO = mapper.map(orderItem, OrderItemVO.class);
		orderItemVO.setToppings(orderItem.getTopping().split(","));
		return orderItemVO;
	}
	
	// - Needs refactoring	
	private BigDecimal getToppingsPrice(String[] toppings) throws resourceNotFoundException {
		
		List<Topping> lstTopping = toppingRepository.findAllById(Arrays.asList(toppings));
		
		if (lstTopping.size()  != toppings.length) {
			throw new resourceNotFoundException(100,"Topping id not found");
		}
			
		return BigDecimal.valueOf(lstTopping.stream().mapToDouble(item -> item.getPrice().doubleValue()).sum());
	
		
	}

	@Override
	public Boolean removeOrderItem(Long orderId, Long orderItemId) {
		orderItemRepository.deleteById(orderItemId);
		return true;
	}

	@Override
	public Boolean updOrderItem(Order order, OrderItemVO orderItemVO) {
		
		String toppings = String.join(",",orderItemVO.getToppings());
//		orderItemRepository.updateOrderItem(toppings, orderItemVO.getOrderItemId());
		
		OrderItem orderItem = orderItemRepository.findById(orderItemVO.getOrderItemId()).get();
		
		orderItem.setQuantity(orderItemVO.getQuantity());
		
		
		//orderItem.setOrder(order);
		
		Pizza pizza =pizzaRepository.findById(orderItemVO.getPizza().getPizzaId()).orElseThrow(() -> new resourceNotFoundException(orderItemVO.getPizza().getPizzaId(),"pizza Id not found"));
		orderItem.setPizza(pizza);
		orderItem.setPrice(pizza.getPrice().add(getToppingsPrice(orderItemVO.getToppings())));
		orderItem.setTopping(toppings);
		
		orderItemRepository.save(orderItem);
		
		return true;

	}
}

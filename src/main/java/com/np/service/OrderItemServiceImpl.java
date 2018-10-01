package com.np.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.np.entity.Customer;
import com.np.entity.ItemToppingId;
import com.np.entity.Order;
import com.np.entity.OrderItem;
import com.np.entity.OrderToppings;
import com.np.entity.Pizza;
import com.np.entity.Topping;
import com.np.repository.OrderItemRepository;
import com.np.repository.OrderRepository;
import com.np.repository.OrderToppingsRepository;
import com.np.repository.PizzaRepository;
import com.np.repository.ToppingRepository;
import com.np.rest.exception.resourceNotFoundException;
import com.np.util.Mapper;
import com.np.vo.OrderItemVO;
import com.np.vo.OrderToppingsVO;
import com.np.vo.ToppingVO;
@Service
public class OrderItemServiceImpl implements IOrderItemService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderToppingsRepository orderToppingsRepository;
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	@Autowired
	private Mapper mapper;

	@Override
	public List<OrderItemVO> getAllOrderItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItemVO getOrderItemByItemId(Long orderId, Long orderItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addOrderItem(OrderItemVO orderItemVO) {
		OrderItem  orderItem = mapper.map(orderItemVO, OrderItem.class);
		
		Order order =orderRepository.findById(orderItemVO.getOrderId()).orElseThrow(() -> new resourceNotFoundException(orderItemVO.getOrderId(),"order Id not found"));
		orderItem.setOrder(order);
		
		Pizza pizza =pizzaRepository.findById(orderItemVO.getPizzaId()).orElseThrow(() -> new resourceNotFoundException(orderItemVO.getPizzaId(),"pizza Id not found"));
		orderItem.setPizza(pizza);
		
		orderItem = orderItemRepository.save(orderItem);
		for (ToppingVO toppingVO:orderItemVO.getToppings() ) {
			OrderToppings orderToppings = new OrderToppings();
			ItemToppingId itemToppingId = new ItemToppingId(orderItem.getOrderItemId(),toppingVO.getToppingId());
			Topping topping = toppingRepository.findById(toppingVO.getToppingId()).orElseThrow(() -> new resourceNotFoundException(orderItemVO.getOrderId(),"topping Id not found"));
			//itemToppingId.setOrderItemId(orderItem.getOrderItemId());
		//	itemToppingId.setToppingId(toppingVO.getToppingId());
			orderToppings.setId(itemToppingId);
			orderToppings.setOrderItem(orderItem);
			orderToppings.setTopping(topping);
			orderToppingsRepository.save(orderToppings);
		}
		//save the  orderItem toppings
//		orderItemVO.getToppings().forEach((toppingVO) ->{	
//		Topping topping = new Topping();
//		topping.setToppingId(toppingVO.getToppingId());
//		OrderToppings orderToppings = new OrderToppings();
//		orderToppings.setId(new ItemToppingId(orderItem.getOrderItemId(),topping.getToppingId() ));
//		orderToppingsRepository.save(orderToppings);});
		return true;
	}
	
	

	@Override
	public OrderToppingsVO getOrderItemToppings(Long orderId, Long orderItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addOrderItemTopping(Long orderId, Long orderItemId, String toppingId) {
		// TODO Auto-generated method stub
		return false;
	}

}

package com.np.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.np.entity.Customer;
import com.np.entity.Order;
import com.np.entity.OrderItem;
import com.np.entity.Topping;
import com.np.repository.CustomerRepository;
import com.np.repository.OrderItemRepository;
import com.np.repository.OrderRepository;
import com.np.rest.exception.ValidationExceptionHandler;
import com.np.rest.exception.resourceNotFoundException;
import com.np.util.Mapper;
import com.np.util.OrderStatus;
import com.np.vo.OrderItemVO;
import com.np.vo.OrderVO;
import com.np.vo.PaymentVO;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired 
	private IOrderItemService orderItemService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ToppingService toppingService;
	
	@Autowired
	private Mapper mapper;
	
	@Override
	public List<OrderVO> getAllOrders() {
		List<Order> orders = orderRepository.findAll();
		//map to VO object
		
		return mapper.mapList(orders, OrderVO.class);
	}

	@Override
	public List<OrderVO> getOrderByDate(Date orderDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderVO getOrderbyId(Long orderId) {
		Order order =orderRepository.findById(orderId).orElseThrow(() -> new resourceNotFoundException(orderId,"order Id not found"));
		
		//map to VO object
		
		return mapper.mapToOrderVO(order);
		
	}

	@Override
	public OrderVO addOrder(OrderVO orderVO) {
		Order  order = mapper.map(orderVO, Order.class);
		//set the local date time
		
		order.setOrderDate(java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
		
		//get the customer
		Customer customer =customerRepository.findById(orderVO.getCustomer().getCustomerId()).orElseThrow(() -> new resourceNotFoundException(orderVO.getCustomer().getCustomerId(),"Customer Id not found"));
		
		order.setCustomer(customer);
		Order newOrder = orderRepository.save(order);
		
		return mapper.map(newOrder, OrderVO.class);
	}
	
	

	@Override
	public OrderVO OrderbyCustomerName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderVO addOrderItem(Long orderId,OrderItemVO orderItemVO) {
		Order order =orderRepository.findById(orderId).orElseThrow(() -> new resourceNotFoundException(orderId,"order Id not found"));

		if (Optional.ofNullable(orderItemVO.getOrderItemId()).orElse((long) 0) != 0) {
			orderItemService.updOrderItem(order, orderItemVO);
		}
		else {
			orderItemService.addOrderItem(order, orderItemVO);
		}	
		
		List<OrderItem> orderItems = orderItemRepository.findByOrder(order);
		
		orderRepository.updateTotalPrice(getTotalPrice(orderItems),orderId );
		
		Order newOrder = orderRepository.findById(orderId).get();
		return mapper.mapToOrderVO(newOrder);
	}
	
	private BigDecimal getTotalPrice(List<OrderItem> orderItems) {
		return BigDecimal.valueOf(orderItems.stream().mapToDouble(item -> item.getPrice().doubleValue()).sum());
		
	}

	@Override
	public Boolean removeOrderItem(Long orderId, Long orderItemId) {
		Order order =orderRepository.findById(orderId).orElseThrow(() -> new resourceNotFoundException(orderId,"order Id not found"));

		orderItemService.removeOrderItem(orderId, orderItemId);
		
		List<OrderItem> orderItems = orderItemRepository.findByOrder(order);
		
		order.setTotalPrice(getTotalPrice(orderItems));
		
		Order newOrder = orderRepository.save(order);
		
		return true;

	}

	@Override
	public OrderVO placeOrder(Long orderId, PaymentVO paymentVO) {
		
		Order order =orderRepository.findById(orderId).orElseThrow(() -> new resourceNotFoundException(orderId,"order Id not found"));

		
		//check the toppings quantity and reduce the available quantity(may throw OptimisticLockException)
		List<OrderItem> orderItems = orderItemRepository.findByOrderOrderId(orderId);
		
		processToppingQuantity(orderItems);
		
		order.setOrderStatus(OrderStatus.COMPLETE.name());
		
		Order newOrder = orderRepository.save(order);
		// TODO Auto-generated method stub
		return mapper.mapToOrderVO(newOrder);
	}

	
	
	private Boolean processToppingQuantity(List<OrderItem> orderItems) {
		HashMap<String, Integer> toppingHash = new HashMap<>();
		
		//get the list of toppings to HaspMap with quantity
		orderItems.forEach((item) -> {
			int quantity = item.getQuantity();
			List<String> toppings = Arrays.asList(item.getTopping().split(","));
			toppings.forEach((topping)-> {
				toppingHash.put(topping, toppingHash.getOrDefault(topping, 0)+quantity);
			});
			
		});
		
		List<Topping> toppings = toppingService.getToppingbyIds(new ArrayList(toppingHash.keySet()));
		
		//update the toppings entity quantityonhand in toppings entity
		
		toppings.forEach((topping) -> {
			int availableQuantity = topping.getAvailableQuantity() - toppingHash.getOrDefault(topping.getToppingId(), 0);
			if (availableQuantity<=0) throw new ValidationExceptionHandler("Insuffcient Quantity " + topping.getToppingId());
			topping.setAvailableQuantity(availableQuantity);
			
		});
		//handle the optimistic lock exception
		toppingService.updateToppings(toppings);
		
		return true;
		
	}

}

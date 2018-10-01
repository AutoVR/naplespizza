package com.np.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.np.entity.Customer;
import com.np.entity.Order;
import com.np.repository.CustomerRepository;
import com.np.repository.OrderRepository;
import com.np.rest.exception.resourceNotFoundException;
import com.np.util.Mapper;
import com.np.vo.OrderVO;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
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
		Optional<Order> order = orderRepository.findById(orderId);
		//map to VO object
		
		return mapper.map(order, OrderVO.class);
		
	}

	@Override
	public Boolean addOrder(OrderVO orderVO) {
		Order  order = mapper.map(orderVO, Order.class);
		//set the local date time
		
		order.setOrderDate(java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
		
		//get the customer
		Customer customer =customerRepository.findById(orderVO.getCustomerId()).orElseThrow(() -> new resourceNotFoundException(orderVO.getCustomerId(),"Customer Id not found"));
		
		order.setCustomer(customer);
		orderRepository.save(order);
		return true;
	}

	@Override
	public OrderVO OrderbyCustomerName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

}

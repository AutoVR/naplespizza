package com.np.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.np.entity.Order;
import com.np.vo.OrderVO;

@Service
public interface IOrderService {

	public List<OrderVO> getAllOrders();
	public List<OrderVO> getOrderByDate(Date orderDate);
	public OrderVO getOrderbyId(Long orderId);
	public Boolean addOrder(OrderVO orderVO);
	public OrderVO OrderbyCustomerName(String firstName, String lastName);
}

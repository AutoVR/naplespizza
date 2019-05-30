package com.np.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.np.entity.Order;
import com.np.vo.OrderItemVO;
import com.np.vo.OrderVO;
import com.np.vo.PaymentVO;

@Service
public interface IOrderService {

	public List<OrderVO> getAllOrders();
	public List<OrderVO> getOrderByDate(Date orderDate);
	public OrderVO getOrderbyId(Long orderId);
	public OrderVO addOrder(OrderVO orderVO);
	public OrderVO OrderbyCustomerName(String firstName, String lastName);
	public OrderVO addOrderItem(Long orderId, OrderItemVO orderItemVO);
	public Boolean removeOrderItem(Long orderId, Long orderItemId);
	public OrderVO placeOrder(Long orderId, PaymentVO paymentVO);
}

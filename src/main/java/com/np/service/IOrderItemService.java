package com.np.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.np.entity.Order;
import com.np.vo.OrderItemVO;
import com.np.vo.OrderVO;

@Service
public interface IOrderItemService {

	public List<OrderItemVO> getAllOrderItem(Long orderId);
	public OrderItemVO getOrderItemByItemId(Long orderId, Long orderItemId);
	public Boolean addOrderItem(Order order, OrderItemVO orderItemVO);
	public Boolean updOrderItem(Order order, OrderItemVO orderItemVO);
	public Boolean removeOrderItem(Long orderId, Long orderItemId);
}

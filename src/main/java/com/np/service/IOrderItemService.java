package com.np.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.np.vo.OrderItemVO;
import com.np.vo.OrderToppingsVO;
import com.np.vo.OrderVO;

@Service
public interface IOrderItemService {

	public List<OrderItemVO> getAllOrderItem();
	public OrderItemVO getOrderItemByItemId(Long orderId, Long orderItemId);
	public boolean addOrderItem(OrderItemVO orderItemVO);
	public OrderToppingsVO getOrderItemToppings(Long orderId,Long  orderItemId);
	public boolean addOrderItemTopping(Long orderId,Long  orderItemId, String toppingId);
}

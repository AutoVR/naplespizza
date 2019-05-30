package com.np.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.np.entity.Order;
import com.np.service.IOrderItemService;
import com.np.service.IOrderService;
import com.np.vo.OrderItemVO;
import com.np.vo.OrderVO;
import com.np.vo.SuccessVO;

@Component
public class PizzaOrderItemControllerImpl implements IPizzaOrderItemController {

	@Autowired
	IOrderService orderService;

	
	@Override
	public ResponseEntity getAllOrderItem(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity getOrderItemByItemId(Long orderId, Long orderItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity addOrderItem(@PathVariable("orderid") Long orderId, @RequestBody OrderItemVO orderItemVO) {
		OrderVO newOrderVO = orderService.addOrderItem(orderId, orderItemVO);
		return  new ResponseEntity<OrderVO>(newOrderVO, HttpStatus.CREATED);
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeOrderItem(@PathVariable("orderid") Long orderId, @PathVariable("orderItemId") Long  orderItemId) {
		 orderService.removeOrderItem(orderId, orderItemId);
	}

	@Override
	public ResponseEntity updOrderItem(@PathVariable("orderid") Long orderId, @PathVariable("orderItemId") Long orderItemId, @RequestBody OrderItemVO orderItemVO) {
		orderItemVO.setOrderItemId(orderItemId);
		
		OrderVO newOrderVO = orderService.addOrderItem(orderId, orderItemVO);
		return  new ResponseEntity<OrderVO>(newOrderVO, HttpStatus.CREATED);
	}

	

	

}

package com.np.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.np.entity.Order;
import com.np.service.IOrderItemService;
import com.np.vo.OrderItemVO;
import com.np.vo.OrderToppingsVO;
import com.np.vo.SuccessVO;

@Component
public class PizzaOrderItemControllerImpl implements IPizzaOrderItemController {

	@Autowired
	IOrderItemService orderItemService;
	
	@Override
	public ResponseEntity getAllOrderItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity getOrderItemByItemId(Long orderItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity addOrderItem(@RequestBody OrderItemVO orderItemVO) {
		orderItemService.addOrderItem(orderItemVO);
		return new ResponseEntity(new SuccessVO("Order Item created Successfully"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity removeOrderItem(OrderItemVO orderItemVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity getOrderItemToppings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity addOrderItemTopping(OrderToppingsVO orderToppingsVO) {
		// TODO Auto-generated method stub
		return null;
	}

}

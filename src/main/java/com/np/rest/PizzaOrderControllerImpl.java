package com.np.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.np.entity.Order;
import com.np.service.IOrderService;
import com.np.vo.OrderVO;
import com.np.vo.PaymentVO;
import com.np.vo.SuccessVO;

@Component
public class PizzaOrderControllerImpl implements IPizzaOrderController{
	@Autowired
	IOrderService orderService;

	@Override
	public ResponseEntity<List<OrderVO>> getOrders() {
		List<OrderVO> orders =orderService.getAllOrders();
		return new ResponseEntity<List<OrderVO>>(orders, !orders.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<OrderVO> getOrderById(@PathVariable("orderId")  Long Id) {
		OrderVO orderVO =orderService.getOrderbyId(Id);
		return new ResponseEntity<OrderVO>(orderVO, orderVO!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND);

	}

	@Override
	public ResponseEntity<OrderVO> addOrder(@RequestBody  OrderVO orderVO) {
		OrderVO newOrderVO = orderService.addOrder(orderVO);
		return  new ResponseEntity<OrderVO>(newOrderVO, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<OrderVO>> getOrderbyCustomerName(@RequestParam("firstname")  String firstName,@RequestParam("lastname") String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity placeOrder(@PathVariable("orderId") Long Id, @RequestBody PaymentVO paymentVO) {
		OrderVO newOrderVO = orderService.placeOrder(Id, paymentVO);
		return new ResponseEntity<OrderVO>(newOrderVO, HttpStatus.OK);
	}

	

}

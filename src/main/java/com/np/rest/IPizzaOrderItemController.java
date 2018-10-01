package com.np.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.np.entity.Order;
import com.np.util.Response;
import com.np.vo.OrderItemVO;
import com.np.vo.OrderToppingsVO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public interface IPizzaOrderItemController {
	
	@GetMapping(value = "/orders/{orderId}/orderItems", produces= {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Return all the pizza orders in a single order", notes="This API returns all the orders from DB", response=List.class)
	@ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
	ResponseEntity getAllOrderItem();

	@GetMapping(value = "/orders/{orderId}/orderItems/{orderItemId}", produces= {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Return all the pizza orders in a order", notes="This API returns all the orders from DB", response=List.class)
	@ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
	ResponseEntity getOrderItemByItemId(Long orderItemId);


	@PostMapping(value = "/orders/{orderid}/orderItems", 
			produces = {MediaType.APPLICATION_JSON_VALUE}, 
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Add a new pizza to the order", notes="The POST API allows to add item to the new order", response=Response.class)
	@ApiResponses(value = { 
			@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "The order item has been added successfully"),
			@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "Invalid Consumer Key"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "The Food does not exist")
	})
	ResponseEntity addOrderItem(@RequestBody OrderItemVO orderItemVO);
	
	@DeleteMapping(value = "/orders/{orderid}/orderItems/{orderItemId}", 
			produces = {MediaType.APPLICATION_JSON_VALUE}, 
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Add a new pizza to the order", notes="The POST API allows to add item to the new order", response=Response.class)
	@ApiResponses(value = { 
			@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "The order item has been added successfully"),
			@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "Invalid Consumer Key"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "The Food does not exist")
	})
	ResponseEntity removeOrderItem( @RequestBody OrderItemVO OrderItemVO);
	
	@GetMapping(value = "/orders/{orderId}/orderItems/{orderItemId}/toppings", produces= {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Return all the pizza orders in a single order", notes="This API returns all the orders from DB", response=List.class)
	@ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
	ResponseEntity getOrderItemToppings();
	
	@PostMapping(value = "/orders/{orderid}/orderItems/{orderItemId}/toppings", 
			produces = {MediaType.APPLICATION_JSON_VALUE}, 
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	ResponseEntity addOrderItemTopping(@RequestBody OrderToppingsVO orderToppingsVO);
	

}

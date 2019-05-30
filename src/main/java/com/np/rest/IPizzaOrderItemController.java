package com.np.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.np.entity.Order;
import com.np.util.Response;
import com.np.vo.OrderItemVO;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public interface IPizzaOrderItemController {
	
	@GetMapping(value = "/orders/{orderId}/orderItems", produces= {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Return all the pizza orders in a single order", notes="This API returns all the orders from DB", response=List.class)
	@ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
	ResponseEntity getAllOrderItem(@PathVariable("orderId") Long orderId);

	@GetMapping(value = "/orders/{orderId}/orderItems/{orderItemId}", produces= {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Return all the pizza orders in a order", notes="This API returns all the orders from DB", response=List.class)
	@ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
	ResponseEntity getOrderItemByItemId(@PathVariable("orderId") Long orderId, @PathVariable("orderItemId")  Long orderItemId);


	@PostMapping(value = "/orders/{orderid}/orderItems", 
			produces = {MediaType.APPLICATION_JSON_VALUE}, 
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Add a new pizza to the order", notes="The POST API allows to add item to the new order", response=Response.class)
	@ApiResponses(value = { 
			@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "The order item has been added successfully"),
			@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "Invalid Consumer Key"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "The pizza/topping not found")
	})
	ResponseEntity addOrderItem(@PathVariable("orderid") Long orderId, @RequestBody OrderItemVO orderItemVO);
	
	@PutMapping(value = "/orders/{orderid}/orderItems/{orderItemId}", 
			produces = {MediaType.APPLICATION_JSON_VALUE}, 
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Update a item in existing order", notes="This method allows to update the existing order item", response=Response.class)
	@ApiResponses(value = { 
			@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "The order item has been updated successfully"),
			@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "Invalid Consumer Key"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "The Order Item/Pizza/Topping not found")
	})
	ResponseEntity updOrderItem(@PathVariable("orderid") Long orderId, @PathVariable("orderItemId") Long  orderItemId, @RequestBody OrderItemVO orderItemVO);
	
	@DeleteMapping(value = "/orders/{orderid}/orderItems/{orderItemId}", 
			produces = {MediaType.APPLICATION_JSON_VALUE}, 
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Add a new pizza to the order", notes="The POST API allows to add item to the new order", response=Response.class)
	@ApiResponses(value = { 
			@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "The order item has been added successfully"),
			@ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "Invalid Consumer Key"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "The Order/Order Item not found")
	})
	void removeOrderItem( @PathVariable("orderid") Long orderId, @PathVariable("orderItemId") Long  orderItemId);
	
	
	

}

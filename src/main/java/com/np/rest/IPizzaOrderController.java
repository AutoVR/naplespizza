package com.np.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.np.util.Response;
import com.np.vo.OrderVO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public interface IPizzaOrderController {
	@GetMapping(value = "/orders", produces= {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Return all the orders", notes="This API returns all the orders from DB", response=List.class)
	@ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
	ResponseEntity<List<OrderVO>> getOrders();
	
	@GetMapping(value = "/orders/{orderId}", produces= {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Return the order by orderid", notes="This API returns  the order from DB based on order id", response=List.class)
	@ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
	ResponseEntity<OrderVO> getOrderById();
	
	@PostMapping(value = "/orders", 
			produces = {MediaType.APPLICATION_JSON_VALUE}, 
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Add a new order with details", notes="The POST API allows to add new order", response=Response.class)
	@ApiResponses(value = { 
			@ApiResponse(code = HttpServletResponse.SC_CREATED, message = "A new order has been added successfully"),
	})
	ResponseEntity addOrder( @RequestBody OrderVO order);
	
	@GetMapping(value = "/orders/search", produces= {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Return all the orders", notes="This API returns all the orders from DB", response=List.class)
	@ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
	ResponseEntity<List<OrderVO>> getOrderbyCustomerName(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName);
	
}

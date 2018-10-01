package com.np.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.np.util.Response;
import com.np.vo.ToppingVO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface IToppingsController {
	@GetMapping(value = "/topping", produces= {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Return all the toppings", notes="This API returns all the toppings from DB", response=List.class)
	@ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
	ResponseEntity<List<ToppingVO>> getToppings();
	
	@GetMapping(value = "/topping/{Id}", produces= {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Return the topping by toppingid", notes="This API returns  the topping from DB based on topping id", response=List.class)
	@ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
	ResponseEntity<ToppingVO> getToppingById(String Id);
	
	@PostMapping(value = "/topping", 
			produces = {MediaType.APPLICATION_JSON_VALUE}, 
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value="Add a new order with details", notes="The POST API allows to add new topping", response=Response.class)
	@ApiResponses(value = { 
			@ApiResponse(code = HttpServletResponse.SC_CREATED, message = "A new topping has been added successfully"),
	})
	ResponseEntity addTopping(@RequestBody ToppingVO Topping);

}

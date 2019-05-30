package com.np.vo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
@Data
public class OrderItemVO {
	private Long orderItemId;
	private BigDecimal  price;
	private PizzaVO pizza;
	private int quantity;
	private String[] toppings;
}

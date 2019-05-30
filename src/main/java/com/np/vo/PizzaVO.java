package com.np.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PizzaVO {
	private int pizzaId;
	private String name;
	private BigDecimal  price;
}

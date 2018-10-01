package com.np.vo;

import java.util.List;

import lombok.Data;
@Data
public class OrderItemVO {
	private int orderItemId;
	private Long orderId;
	private float price;
	private int pizzaId;
	private List<ToppingVO> toppings;
}

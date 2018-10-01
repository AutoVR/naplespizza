package com.np.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="orderItem")
public class OrderItem {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderItemId;
	
	@ManyToOne
	@JoinColumn(name = "orderId", nullable = false)
	private Order order;
	
	
	private float price;
	
	 @ManyToOne
	 @JoinColumn(name = "pizzaId", nullable = false)
	private Pizza pizza;
	
}

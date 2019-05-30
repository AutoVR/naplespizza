package com.np.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
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
	
	@ManyToOne(cascade= CascadeType.MERGE)
	@JoinColumn(name = "ORDER_ID", nullable = false)
	private Order order;
	
	
	private BigDecimal price;
	
	private int quantity;
	
	private String topping;
	
	@ManyToOne
	@JoinColumn(name = "pizzaId", nullable = false)
	private Pizza pizza;
	 
	 
	
}

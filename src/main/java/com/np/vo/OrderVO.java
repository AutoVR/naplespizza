package com.np.vo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
public class OrderVO {
	private Long orderId;
	//private Long customerId;
	private CustomerVO customer;
	private BigDecimal  totalPrice;
	private String orderStatus;
	private Date orderDate;
	private boolean isDelivery;
	private List<OrderItemVO> orderItems;
}

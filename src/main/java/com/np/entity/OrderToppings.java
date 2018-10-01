package com.np.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="order_toppings")
public class OrderToppings {
	
		
	@EmbeddedId
    private ItemToppingId id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderItemId")
    private OrderItem orderItem;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("toppingId")
    private Topping topping;
	
}

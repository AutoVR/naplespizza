package com.np.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ItemToppingId implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "orderItemId")
    private Long orderItemId;
 
    @Column(name = "toppingId")
    private String toppingId;

	public ItemToppingId(Long orderItemId, String toppingId) {
		super();
		this.orderItemId = orderItemId;
		this.toppingId = toppingId;
	}
    
	public ItemToppingId() {
		super();
	}
    
}

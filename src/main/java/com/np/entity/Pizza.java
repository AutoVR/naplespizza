package com.np.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="pizza")
public class Pizza {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pizzaId;
	private String name;
	private BigDecimal price;
}

package com.np.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.np.entity.OrderToppings;

public interface OrderToppingsRepository extends JpaRepository<OrderToppings, Long> {

}

package com.np.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.np.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}

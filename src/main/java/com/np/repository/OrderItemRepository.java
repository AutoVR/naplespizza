package com.np.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.np.entity.Order;
import com.np.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	List<OrderItem> findByOrder(Order order);
	List<OrderItem> findByOrderOrderId(Long  orderId);
	
	@Transactional
    @Modifying
    @Query("UPDATE OrderItem O SET O.topping = :topping  WHERE O.orderItemId = :orderItemId")
    int updateOrderItem(@Param("topping") String topping, @Param("orderItemId") Long orderItemId);
}

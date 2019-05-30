package com.np.repository;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.boot.spi.JpaOrmXmlPersistenceUnitDefaultAware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.np.entity.Customer;
import com.np.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByCustomer(Customer customer);

	@Transactional
    @Modifying
    @Query("UPDATE Order O SET O.totalPrice = :totalPrice  WHERE O.orderId = :orderId")
    int updateTotalPrice(@Param("totalPrice") BigDecimal totalPrice, @Param("orderId") Long orderId);
}

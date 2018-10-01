package com.np.repository;

import java.util.List;

import org.hibernate.boot.spi.JpaOrmXmlPersistenceUnitDefaultAware;
import org.springframework.data.jpa.repository.JpaRepository;

import com.np.entity.Customer;
import com.np.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	//List<Order> findorderBycustomerId(Long customerId);

}

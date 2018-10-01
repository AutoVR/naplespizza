package com.np.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.np.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

	//@Query("select c from customer c where upper(c.firstName) like concat('%', upper(?1), '%') or upper(c.lastName) like concat('%', upper(?2), '%')")
	//List<Customer> findByNames(String firstName, String lastName);
}

package com.np.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.np.entity.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

}

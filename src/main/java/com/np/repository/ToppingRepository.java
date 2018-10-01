package com.np.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.np.entity.Topping;

public interface ToppingRepository extends JpaRepository<Topping, String> {

}

package com.springboot.restaurant_application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.restaurant_application.model.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{

	 List<Order> findByCartId(Integer cartId);
	 List<Order> findAllByOrderByOrderIdDesc();
}

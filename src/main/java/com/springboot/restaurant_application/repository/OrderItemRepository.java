package com.springboot.restaurant_application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.restaurant_application.model.Order;
import com.springboot.restaurant_application.model.OrderItems;

public interface OrderItemRepository extends JpaRepository<OrderItems, Integer>{

	List<OrderItems> findByOrder(Order order);
}

package com.springboot.restaurant_application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.restaurant_application.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

	List<CartItem> findByCartId(Integer cartId);
}

package com.springboot.restaurant_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.restaurant_application.model.Cart;
import com.springboot.restaurant_application.model.User;

public interface CartRepository extends JpaRepository<Cart, Integer>{

	Cart findByUser(User user);

} 

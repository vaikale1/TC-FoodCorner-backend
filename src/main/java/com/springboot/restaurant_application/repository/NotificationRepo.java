package com.springboot.restaurant_application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.restaurant_application.model.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Long>{

	List<Notification> findByCartId(int cartId);

}

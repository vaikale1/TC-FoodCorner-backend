package com.springboot.restaurant_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restaurant_application.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);
}

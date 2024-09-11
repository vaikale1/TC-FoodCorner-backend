package com.springboot.restaurant_application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restaurant_application.model.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, Integer> {
	Optional<FoodItem> findByItemName(String itemName);
	List<FoodItem> findByCategory(String category);
}

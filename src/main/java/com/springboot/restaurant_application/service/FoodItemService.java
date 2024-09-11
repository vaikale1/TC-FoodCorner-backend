package com.springboot.restaurant_application.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.coyote.BadRequestException;

import com.springboot.restaurant_application.model.FoodItem;

public interface FoodItemService {

	public FoodItem addFoodItem(FoodItem foodItem) throws BadRequestException;
	public FoodItem findFoodItem(int id) throws Exception;
	public void deleteFoodItem(int id) throws Exception;
	public FoodItem updateFoodItem(FoodItem foodItem,int id)throws Exception;
	public List<FoodItem> findAllFoodItems();
	public FoodItem findByItemName(String itemName);
	public List<FoodItem> getFoodItemsByCategory(String category);
	/*
	 * public List<FoodItem> getFoodItemsByCategory(String category)throws
	 * NoSuchElementException;
	 */
}

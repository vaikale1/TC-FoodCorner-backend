package com.springboot.restaurant_application.controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restaurant_application.model.FoodItem;
import com.springboot.restaurant_application.repository.FoodItemRepository;
import com.springboot.restaurant_application.service.FoodItemService;

import ch.qos.logback.classic.Logger;


@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(AdminController.class);

	@Autowired
	public FoodItemService foodItemService;
	
	@GetMapping("/home")
	public String Home() {
		return "welcome admin";
	}
	
	@PostMapping("/fooditem/addfooditem")
	public FoodItem addFoodItem(@RequestBody FoodItem foodItem) throws Exception {
		try {
		 FoodItem createdFoodItem=foodItemService.addFoodItem(foodItem); 
		 return createdFoodItem;
		}
		catch(BadRequestException e) {
			logger.error("Food item is already present",e.getMessage());
			throw e;
		}
		
		catch(Exception e) {
			logger.error("Food item updting error",e.getMessage());
			throw e;
		}
		
		
	}
	
	@GetMapping("/fooditem")
	public List<FoodItem> getAllFoodItem() throws Exception{
		List<FoodItem> foodItems=foodItemService.findAllFoodItems();
		return foodItems;
		/*
		 * try { List<FoodItem> foodItems=foodItemService.findAllFoodItems(); return
		 * foodItems; } catch(BadRequestException e) { Logger. } catch (Exception e) {
		 * // TODO: handle exception }
		 */
	}
	
	@PutMapping("/fooditem/updatefooditem/{itemid}")
	public FoodItem updateFoodItem(@RequestBody FoodItem foodItem,@PathVariable("itemid") int id) throws Exception {
		
		try {
			FoodItem updateItem=foodItemService.updateFoodItem(foodItem,id);
			return updateItem;
		}catch(BadRequestException e) {
			logger.error("Food item is already present",e.getMessage());
			throw e;
		}
		
		catch(Exception e) {
			logger.error("Food item updating error ",e.getMessage());
			throw e;
		}
	}
	
	@DeleteMapping("/fooditem/deleteitem/{itemid}")
	public ResponseEntity<?> deleteFoodItem(@PathVariable("itemid") int id) throws Exception {
		foodItemService.deleteFoodItem(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}

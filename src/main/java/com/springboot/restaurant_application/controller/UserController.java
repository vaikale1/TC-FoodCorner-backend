package com.springboot.restaurant_application.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restaurant_application.model.FoodItem;
import com.springboot.restaurant_application.model.User;
import com.springboot.restaurant_application.repository.UserRepository;
import com.springboot.restaurant_application.service.FoodItemService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FoodItemService foodItemService;
	
	@PostMapping("/registration")
	public User addUser(@RequestBody User user) throws Exception{
		User isExist=userRepository.findByEmail(user.getEmail());
		if(isExist!=null) {
			throw new Exception("Email already exists with email"+user.getEmail());
		}
		User saveUser=userRepository.save(user);
		return saveUser;
	}
	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable int userId) throws Exception {
		userRepository.deleteById(userId);
		return "User deleted successfully";
	}
	
	
	@GetMapping("/showFoodItems")
	public List<FoodItem> getAllFoodItem() throws Exception{
		List<FoodItem> foodItems=foodItemService.findAllFoodItems();
		return foodItems;
	}
	@GetMapping("/home")
	public List<FoodItem> getFoodItemsByCategory(@RequestParam String category) {
        return foodItemService.getFoodItemsByCategory(category);
    }
}

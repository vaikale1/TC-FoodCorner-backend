package com.springboot.restaurant_application.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restaurant_application.enums.UserRole;
import com.springboot.restaurant_application.model.Cart;
import com.springboot.restaurant_application.model.CartItem;
import com.springboot.restaurant_application.model.User;
import com.springboot.restaurant_application.repository.CartItemRepository;
import com.springboot.restaurant_application.repository.CartRepository;
import com.springboot.restaurant_application.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private FoodItemService foodItemService;

	@Override
	public User findUserById(int id) throws Exception {
		// TODO Auto-generated method stub
		Optional<User> opt=userRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new Exception("user not found with id "+id);
		
	}

	public User registerUser(String email, String password, String fullName, UserRole role) {
		System.out.println("Checking email: " + email);
	    User existingUser = userRepository.findByEmail(email);
	    System.out.println("Existing user: " + existingUser);
	    if (existingUser != null) {
	        throw new IllegalArgumentException("Email already exists.");
	    }

        // Create and save the new user
        User user = new User();
        user.setEmail(email);
        user.setPassword(password); // Hash the password
        user.setFullName(fullName);
        user.setAccountType(role);
        Cart cart = new Cart();
        cart.setUser(user); // Set the user for the cart
        user.setCart(cart); 
        return userRepository.save(user);
    }
	 public void deleteUserById(int userId) {
	        userRepository.deleteById(userId);
	    }
	    
	    public void deleteUsersByIds(List<Integer> userIds) {
	        for (int userId : userIds) {
	            deleteUserById(userId);
	        }
	    }
	

}

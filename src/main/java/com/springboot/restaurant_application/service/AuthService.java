package com.springboot.restaurant_application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restaurant_application.model.User;
import com.springboot.restaurant_application.repository.UserRepository;

@Service
public class AuthService {

	 @Autowired
	    private UserRepository userRepository;

	    public User authenticate(String username, String password) {
	        User user = userRepository.findByEmail(username);
	        if (user != null && user.getPassword().equals(password)) {
	            return user;
	        }
	        return null;
	    }
}

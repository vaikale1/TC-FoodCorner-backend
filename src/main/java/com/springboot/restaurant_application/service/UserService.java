package com.springboot.restaurant_application.service;

import com.springboot.restaurant_application.model.Cart;
import com.springboot.restaurant_application.model.User;

import jakarta.servlet.http.HttpSession;

public interface UserService {

	public User findUserById(int id)throws Exception;
	

}

package com.springboot.restaurant_application.service;

import com.springboot.restaurant_application.model.User;

public interface UserService {

	public User findUserById(int id)throws Exception;
}

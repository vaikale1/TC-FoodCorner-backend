package com.springboot.restaurant_application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restaurant_application.model.User;
import com.springboot.restaurant_application.repository.UserRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserById(int id) throws Exception {
		// TODO Auto-generated method stub
		Optional<User> opt=userRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new Exception("user not found with id "+id);
		
	}

}

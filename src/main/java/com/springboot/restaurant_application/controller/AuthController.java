package com.springboot.restaurant_application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restaurant_application.dtos.UserDTO;
import com.springboot.restaurant_application.enums.UserRole;
import com.springboot.restaurant_application.model.LoginRequest;
import com.springboot.restaurant_application.model.LoginResponse;
import com.springboot.restaurant_application.model.User;
import com.springboot.restaurant_application.service.AuthService;
import com.springboot.restaurant_application.service.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class AuthController {

	@Autowired
    private AuthService authService;
	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

		String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
	    User user = authService.authenticate(username, password);
	    if (user != null) {
	        String redirectUrl = user.getAccountType().name().equals("CUSTOMER") ? "/user/home" : "/admin/home";
	        LoginResponse loginResponse = new LoginResponse(user, redirectUrl);
            return ResponseEntity.ok(loginResponse);
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserDTO userRegistrationDTO) {
	    try {
	        User createdUser = userService.registerUser(
	            userRegistrationDTO.getEmail(),
	            userRegistrationDTO.getPassword(),
	            userRegistrationDTO.getFullName(),
	            UserRole.CUSTOMER
	        );
	        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }


        return ResponseEntity.ok("Logged out successfully");
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUsers(@RequestBody List<Integer> userIds) {
        userService.deleteUsersByIds(userIds);
        return ResponseEntity.ok("Users deleted successfully");
    }
}

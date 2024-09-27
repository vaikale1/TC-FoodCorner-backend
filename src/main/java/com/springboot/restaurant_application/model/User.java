package com.springboot.restaurant_application.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.restaurant_application.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="restaurant_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="full_name")
	private String fullName;

	@OneToOne(mappedBy = "user", cascade = {CascadeType.
			DETACH
			,
			CascadeType.
			MERGE
			,
			CascadeType.
			PERSIST
			,
			CascadeType.
			REFRESH
			})
    private Cart cart;
	public int getUserId() {
		return userId;
	}
	@JsonIgnore
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public UserRole getAccountType() {
		return accountType;
	}
	public void setAccountType(UserRole accountType) {
		this.accountType = accountType;
	}
	@Column(name="account_type")
	private UserRole accountType;
	
	
}

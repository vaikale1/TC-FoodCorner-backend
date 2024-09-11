package com.springboot.restaurant_application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@Column(name="phone_no")
	private String phoneNo;
	public int getUserId() {
		return userId;
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
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public User(int userId, String email, String password, String fullName, String phoneNo) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.phoneNo = phoneNo;
	}

	public User(String email, String password, String fullName, String phoneNo) {
		super();
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.phoneNo = phoneNo;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", fullName=" + fullName
				+ ", phoneNo=" + phoneNo + "]";
	}
	
}

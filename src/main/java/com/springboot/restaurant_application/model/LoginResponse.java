package com.springboot.restaurant_application.model;

public class LoginResponse {

	 private User user;
	 private String redirectUrl;
	public LoginResponse(User user, String redirectUrl) {
		super();
		this.user = user;
		this.redirectUrl = redirectUrl;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	 
}

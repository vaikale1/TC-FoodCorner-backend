package com.springboot.restaurant_application.model;

import java.sql.Date;
import java.util.Objects;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "cart_items")
public class CartItem {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int itemId;
	
	@ManyToOne
	(cascade = {CascadeType.
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
	@JoinColumn
	(name =
	"cart_id"
	)
	@JsonIgnore

	private
	Cart cart;
	
	
	
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public CartItem(int itemId, int quantity) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
	}
	
	public CartItem(int itemId) {
		super();
		this.itemId = itemId;
	}
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity =quantity;
	}
	private int quantity;
	
    
	
}

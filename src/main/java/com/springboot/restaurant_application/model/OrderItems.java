package com.springboot.restaurant_application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="userOrderItem")
public class OrderItems {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int itemId;
	private int quantity;
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
	"order_id"
	)
	@JsonIgnore

	private
	Order order;
	public OrderItems(int itemId, int quantity, Order order) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.order = order;
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
		this.quantity = quantity;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public OrderItems() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}

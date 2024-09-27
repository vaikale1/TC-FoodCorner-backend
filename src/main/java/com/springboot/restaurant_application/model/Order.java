package com.springboot.restaurant_application.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user_order")
public class Order {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer orderId;

	    private String orderStatus;

	    private Integer cartId;
	    @OneToMany
		(mappedBy =
		"order"
		,
		cascade = {CascadeType.
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
		List<OrderItems> orderItems;

		public List<OrderItems> getOrderItems() {
			return orderItems;
		}

		public void setOrderItems(List<OrderItems> orderItems) {
			this.orderItems = orderItems;
		}

		public Order(Integer cartId) {
			super();
			this.cartId = cartId;
		}

		public Order(String orderStatus, Integer cartId) {
			super();
			this.orderStatus = orderStatus;
			this.cartId = cartId;
			this.orderItems = new ArrayList<>();
		}

		public Integer getOrderId() {
			return orderId;
		}

		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
		}

		public String getOrderStatus() {
			return orderStatus;
		}

		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}

		public Integer getCartId() {
			return cartId;
		}

		public void setCartId(Integer cartId) {
			this.cartId = cartId;
		}

		public Order() {
			super();
			// TODO Auto-generated constructor stub
		}
    

    
}

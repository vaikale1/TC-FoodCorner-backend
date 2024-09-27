package com.springboot.restaurant_application.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionMessage.ItemsBuilder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@Table(name = "carts")
public class Cart {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private String title;
    
	@OneToMany
	(mappedBy =
	"cart"
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
	List<CartItem> cartItems;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToOne
    @JoinColumn(name = "user_id") 
    private User user;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Cart(String title) {
		super();
		this.title = title;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void addRecord(CartItem cartItem){
		if(cartItems==null){
			cartItems=new ArrayList<>();
			}
		boolean itemExists = false;

	    for (CartItem item : cartItems) {
	        if (item.getItemId() == cartItem.getItemId()) {
	            
	            item.setQuantity(item.getQuantity() + 1);
	            itemExists = true;
	            break;
	        }
	    }

	    if (!itemExists) {
	       
	        cartItems.add(cartItem);
	        cartItem.setCart(this);
	    }

	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	public boolean removeRecord(int itemId) {
        if (cartItems != null) {
            Iterator<CartItem> iterator = cartItems.iterator();
            while (iterator.hasNext()) {
                CartItem item = iterator.next();
                if (item.getId() == itemId) {
                    iterator.remove(); 
                    return true;
                }
            }
        }
        return false; 
    }
	}
	



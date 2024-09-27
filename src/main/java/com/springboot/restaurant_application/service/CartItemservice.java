package com.springboot.restaurant_application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restaurant_application.model.Cart;
import com.springboot.restaurant_application.model.CartItem;
import com.springboot.restaurant_application.model.FoodItem;
import com.springboot.restaurant_application.model.User;
import com.springboot.restaurant_application.repository.CartItemRepository;
import com.springboot.restaurant_application.repository.CartRepository;
import com.springboot.restaurant_application.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CartItemservice {

	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public Cart addCart(Cart cart) {
		return this.cartRepository.save(cart);
		
	}
	public Cart findById(int id) throws Exception {
		Optional<Cart> optional=cartRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new Exception("Cart not found with id"+id);
	}
	public List<CartItem> getAllCartItems(int id) throws Exception{
		Optional<Cart> optional=cartRepository.findById(id);
        Cart theCart=null;
		if(optional.isPresent()) {
			 theCart=optional.get();
			 return theCart.getCartItems();
		}
		throw new Exception("Cart not found with id"+id);
		
	}
	@Transactional
	public void addCartItem(CartItem cartItem) {
		this.cartItemRepository.save(cartItem);	
	}
	@Transactional
	public void deleteCartItemById(int id) {
		Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
	    
	    if (optionalCartItem.isPresent()) {
	        CartItem cartItem = optionalCartItem.get();
	       
	        if (cartItem.getQuantity() > 1) {
	            
	            cartItem.setQuantity(cartItem.getQuantity() - 1);
	            cartItemRepository.save(cartItem);
	        } else {
	            
	            cartItemRepository.deleteById(id);
	        }
	    } else {
	        
	        System.out.println("CartItem with id " + id + " not found.");
	    }
	}
	@Transactional
	public void clearCartItem(int id) {
Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
	    
	    if (optionalCartItem.isPresent()) {
	            cartItemRepository.deleteById(id);
	    } else {
	        System.out.println("CartItem with id " + id + " not found.");
	    }
	}
	@Transactional
	public void clearCart(int id) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
	    if (optionalCartItem.isPresent()) {  
	            cartItemRepository.deleteById(id);
	    } else {
	        
	        System.out.println("CartItem with id " + id + " not found.");
	    }
	}
	public Integer getCartIdForUser(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Cart cart = cartRepository.findByUser(user);
            return cart != null ? cart.getId() : null; 
        }
        return null; 
    }
	public List<Integer> getCartItemIds(List<CartItem> cartItems) {
	    return cartItems.stream()
	                    .map(CartItem::getId) 
	                    .collect(Collectors.toList());
	}
}

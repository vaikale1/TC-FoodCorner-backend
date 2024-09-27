package com.springboot.restaurant_application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restaurant_application.model.Cart;
import com.springboot.restaurant_application.model.CartItem;
import com.springboot.restaurant_application.model.Order;
import com.springboot.restaurant_application.model.OrderItems;
import com.springboot.restaurant_application.repository.CartItemRepository;
import com.springboot.restaurant_application.repository.CartRepository;
import com.springboot.restaurant_application.repository.NotificationRepo;
import com.springboot.restaurant_application.repository.OrderItemRepository;
import com.springboot.restaurant_application.repository.OrderRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	
	 @Autowired
	    private CartRepository cartRepository;
	 @Autowired
	 private OrderItemRepository orderItemRepository;
	 @Autowired
	 private NotificationRepo notificationRepo;

	    
	 @Autowired
	 private CartItemRepository cartItemRepository;
	 public Order saveOrder(Order order) {
	        return orderRepository.save(order);
	    }

	    public Optional<Order> getOrderById(Integer id) {
	        return orderRepository.findById(id);
	    }

		public List<Order> getAllOrders() {
			return orderRepository.findAllByOrderByOrderIdDesc();
		}
		public Optional<Cart> getCartByCartId(Integer cartId) {
	        return cartRepository.findById(cartId);
	    }
		public Order findById(int id) throws Exception {
			Optional<Order> optional=orderRepository.findById(id);
			if(optional.isPresent()) {
				return optional.get();
			}
			throw new Exception("Order not found with id"+id);
		}
		public List<OrderItems> convertCartItemsToOrderItems(List<CartItem> cartItems, Order order) {
		    return cartItems.stream()
		            .map(cartItem -> new OrderItems(
		                    cartItem.getItemId(),
		                    cartItem.getQuantity(),
		                    order))
		            .collect(Collectors.toList());
		}
		public List<OrderItems> getOrderItems(int orderId){
			
			Optional<Order> optionalOrder = orderRepository.findById(orderId);

	        if (optionalOrder.isPresent()) {
	            Order order = optionalOrder.get();
	            return orderItemRepository.findByOrder(order);
	        } else {
	            // Handle the case where the order is not found
	            return List.of(); // Return an empty list or handle it based on your requirement
	        }
			
		}
		public List<Order> getOrdersByCartId(Integer cartId) {
		    return orderRepository.findByCartId(cartId);
		}
		public void deleteNotification(Long id) {
	        notificationRepo.deleteById(id);
	    }
 
}

package com.springboot.restaurant_application.controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.apache.logging.log4j.LogManager;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restaurant_application.model.Cart;
import com.springboot.restaurant_application.model.CartItem;
import com.springboot.restaurant_application.model.FoodItem;
import com.springboot.restaurant_application.model.Notification;
import com.springboot.restaurant_application.model.Order;
import com.springboot.restaurant_application.model.OrderItems;
import com.springboot.restaurant_application.model.User;
import com.springboot.restaurant_application.repository.FoodItemRepository;
import com.springboot.restaurant_application.repository.NotificationRepo;
import com.springboot.restaurant_application.repository.UserRepository;
import com.springboot.restaurant_application.service.CartItemservice;
import com.springboot.restaurant_application.service.FoodItemService;
import com.springboot.restaurant_application.service.OrderService;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(AdminController.class);

	@Autowired
	public FoodItemService foodItemService;
	@Autowired
	private CartItemservice cartItemservice;
	
	@Autowired
	public OrderService orderService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private NotificationRepo notificationRepository;

	@GetMapping("/home")
	public List<FoodItem> getAdminHome(HttpServletRequest request) {
		List<FoodItem> foodItems=foodItemService.findAllFoodItems();
		return foodItems;
	}
	
	@PostMapping("/fooditem/addfooditem")
	public FoodItem addFoodItem(@RequestBody FoodItem foodItem) throws Exception {
		try {
		 FoodItem createdFoodItem=foodItemService.addFoodItem(foodItem); 
		 return createdFoodItem;
		}
		catch(BadRequestException e) {
			logger.error("Food item is already present",e.getMessage());
			throw e;
		}
		
		catch(Exception e) {
			logger.error("Food item updting error",e.getMessage());
			throw e;
		}
		
		
	}
	
	
	
	@PutMapping("/fooditem/updatefooditem/{itemid}")
	public FoodItem updateFoodItem(@RequestBody FoodItem foodItem,@PathVariable("itemid") int id) throws Exception {
		
		try {
			FoodItem updateItem=foodItemService.updateFoodItem(foodItem,id);
			return updateItem;
		}catch(BadRequestException e) {
			logger.error("Food item is already present",e.getMessage());
			throw e;
		}
		
		catch(Exception e) {
			logger.error("Food item updating error ",e.getMessage());
			throw e;
		}
	}


	
	@DeleteMapping("/fooditem/deleteitem/{itemid}")
	public ResponseEntity<?> deleteFoodItem(@PathVariable("itemid") int id) throws Exception {
		foodItemService.deleteFoodItem(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok().body(orders);
    }
	 @GetMapping("/{orderId}/cart-items")
	    public ResponseEntity<List<OrderItems>> getCartItemsByOrderId(@PathVariable Integer orderId) {
		 Optional<Order> orderOptional = orderService.getOrderById(orderId);
	        if (orderOptional.isPresent()) {
	            Order order = orderOptional.get();
	            List<OrderItems> orderItems=orderService.getOrderItems(order.getOrderId());

	            return ResponseEntity.ok().body(orderItems);
	        }
	        return ResponseEntity.notFound().build();
	    }
	 @GetMapping("/getFoodItem/{foodId}")
		public FoodItem getFoodItem(@PathVariable int foodId) throws Exception {
			return this.foodItemService.findFoodItem(foodId);
		}
	 @GetMapping("/getCartItems/{cartId}")
		public List<CartItem> getCartItems(@PathVariable int cartId) throws Exception{
			return this.cartItemservice.getAllCartItems(cartId);
		}
	 
	 
	 @PutMapping("/order/confirm/{orderId}")
	 public Order confirmStatus(@PathVariable int orderId) {
		
		 Optional<Order> order=this.orderService.getOrderById(orderId);
		 if(order.isPresent()) {
			 Order currOrder=order.get();
			 int cartId=currOrder.getCartId();
			 Notification notification = new Notification();
		      notification.setMessage("Your order #" + orderId + " is confirmed");
		        notification.setTimestamp(LocalDateTime.now());// Save the notification to the database
		        notification.setCartId(cartId);
		        notificationRepository.save(notification);
			 currOrder.setOrderStatus("Confirmed");
			 orderService.saveOrder(currOrder);
			 return currOrder;
		 }
		 return null;
		 
	 }
	
}

package com.springboot.restaurant_application.controller;


import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import com.springboot.restaurant_application.model.Cart;
import com.springboot.restaurant_application.model.CartItem;
import com.springboot.restaurant_application.model.FoodItem;
import com.springboot.restaurant_application.model.Notification;
import com.springboot.restaurant_application.model.Order;
import com.springboot.restaurant_application.model.OrderItems;
import com.springboot.restaurant_application.model.User;
import com.springboot.restaurant_application.repository.NotificationRepo;
import com.springboot.restaurant_application.repository.UserRepository;
import com.springboot.restaurant_application.service.CartItemservice;
import com.springboot.restaurant_application.service.FoodItemService;
import com.springboot.restaurant_application.service.FoodItemServiceImpl;
import com.springboot.restaurant_application.service.OrderService;
import com.springboot.restaurant_application.service.UserService;
import com.springboot.restaurant_application.service.UserServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	public OrderService orderService;
	@Autowired
	
	private FoodItemService foodItemService;
	@Autowired
	private FoodItemServiceImpl foodItemServiceImpl;
	
	@Autowired
	private UserService userService;
	@Autowired
	private CartItemservice cartItemservice;
	@Autowired
	private NotificationRepo notificationRepository;
	
	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable int userId) throws Exception {
		userRepository.deleteById(userId);
		return "User deleted successfully";
	}
	
	
	@GetMapping("/showFoodItems")
	public List<FoodItem> getAllFoodItem() throws Exception{
		List<FoodItem> foodItems=foodItemService.findAllFoodItems();
		return foodItems;
	}
	@GetMapping("/home")
	public List<FoodItem> getFoodItemsByCategory(@RequestParam String category) {
        return foodItemService.getFoodItemsByCategory(category);
    }
	
	@PostMapping("/addcart")
	public Cart addCart(@RequestBody Cart theCart) {
		return this.cartItemservice.addCart(theCart);
	}
	@PostMapping("/addcartitem/{cartId}")
	public Cart addcartItem(@PathVariable int cartId,@RequestBody CartItem cartItem) throws Exception {
		Cart theCart=this.cartItemservice.findById(cartId);
		cartItem.setQuantity(1);
		if(cartItem.getItemId()!=0 ) {
			theCart.addRecord(cartItem);
			this.cartItemservice.addCart(theCart);
			return theCart;
		}else {
			throw new Exception("bad data provided");
		}

	}
	 @PostMapping("/cart/confirm")
	    public ResponseEntity<Order> createOrder(@RequestParam Integer cartId, @RequestParam String orderStatus) {
		 try {
		        List<CartItem> cartItems = cartItemservice.getAllCartItems(cartId);
		       
		        Order newOrder = new Order(orderStatus, cartId);
		       
		        List<OrderItems> orderItems = orderService.convertCartItemsToOrderItems(cartItems, newOrder);

		        System.out.println("xx");
		      
		        newOrder.setOrderItems(orderItems);
		        
		        Order savedOrder = orderService.saveOrder(newOrder);
		        return ResponseEntity.ok(savedOrder);
		    } catch (Exception e) {
		        
		        e.printStackTrace();
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                             .body(null);
		    }
	    }
	 @DeleteMapping("/cart/clearcart/{cartItemId}")
	 public void clearCart(@PathVariable Integer cartItemId) {
		 this.cartItemservice.clearCartItem(cartItemId);
	 }


	@GetMapping("/getCartItems/{cartId}")
	public List<CartItem> getCartItems(@PathVariable int cartId) throws Exception{
		return this.cartItemservice.getAllCartItems(cartId);
	}
	
	@GetMapping("/getCart/{cartId}")
	public Cart getCart(@PathVariable int cartId) throws Exception {
		return this.cartItemservice.findById(cartId);
	}
	
	@GetMapping("/getFoodItem/{foodId}")
	public FoodItem getFoodItem(@PathVariable int foodId) throws Exception {
		return this.foodItemServiceImpl.findFoodItem(foodId);
	}
	@DeleteMapping("/deleteCartitem/{cartItemId}")
	public String deleteCartItem(@PathVariable int cartItemId) throws Exception {
		this.cartItemservice.deleteCartItemById(cartItemId);
		return "item deleted";
        
	}
	@GetMapping("/orders/cart/{cartId}")
	public ResponseEntity<List<Order>> getAllOrdersByCartId(@PathVariable Integer cartId) {
	    List<Order> orders = orderService.getOrdersByCartId(cartId);
	    Collections.reverse(orders);
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

	 @GetMapping("/notification/{cartId}")
	 public List<Notification> getNotificationsByCartId(@PathVariable int cartId) {
	     return notificationRepository.findByCartId(cartId);
	 }

	 
	 @GetMapping("/{userId}/cartId")
	    public ResponseEntity<Integer> getCartId(@PathVariable int userId) {
	        Integer cartId = cartItemservice.getCartIdForUser(userId);
	        if (cartId == null) {
	            return ResponseEntity.notFound().build(); // Return 404 if no cart found
	        }
	        return ResponseEntity.ok(cartId);
	    }
	 @DeleteMapping("notification/{id}")
	    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
	        orderService.deleteNotification(id);
	        return ResponseEntity.noContent().build();
	    }
	
	
}

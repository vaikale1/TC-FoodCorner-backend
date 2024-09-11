package com.springboot.restaurant_application.service;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restaurant_application.model.FoodItem;
import com.springboot.restaurant_application.repository.FoodItemRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FoodItemServiceImpl implements FoodItemService {

	@Autowired
	private FoodItemRepository foodItemRepository;

	@Override
	public FoodItem addFoodItem(FoodItem foodItem) throws BadRequestException {
		// TODO Auto-generated method stub
		FoodItem addItem=new FoodItem();
		addItem.setItemName(foodItem.getItemName());
		addItem.setItemImage(foodItem.getItemImage());
		addItem.setCategory(foodItem.getCategory());
		addItem.setDescription(foodItem.getDescription());
		addItem.setPrice(foodItem.getPrice());
		
			FoodItem getFoodItem=findByItemName(foodItem.getItemName());
			if(getFoodItem!=null) {
				throw new BadRequestException("fooditem is Already Present");
			}
			FoodItem foodItem2=foodItemRepository.save(addItem);
			return foodItem2;
			
		}
	
		/*
		 * if(foodItem) if(foodItem.getItemName().equals(addItem)) { throw new
		 * BadRequestException(); }
		 */
		
	

	@Override
	public FoodItem findFoodItem(int id) throws Exception {
		
		Optional<FoodItem> optional=foodItemRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new Exception("FoodItem not found with id"+id);
	}

	@Override
	public void deleteFoodItem(int id) throws Exception {
		// TODO Auto-generated method stub
		findFoodItem(id);
		foodItemRepository.deleteById(id);
		
	}

	@Override
	public FoodItem updateFoodItem(FoodItem foodItem, int id) throws Exception {
		// TODO Auto-generated method stub
		FoodItem oldFoodItem=findFoodItem(id);
		boolean isNameUpdated = foodItem.getItemName() != null && !foodItem.getItemName().equals(oldFoodItem.getItemName());
		
		if(foodItem.getItemImage()!=null) {
			oldFoodItem.setItemImage(foodItem.getItemImage());
			
		}
		if(foodItem.getPrice()!=0) {
			oldFoodItem.setPrice(foodItem.getPrice());
			
		}
		if(foodItem.getDescription()!=null) {
			oldFoodItem.setDescription(foodItem.getDescription());
			
		}
		if(foodItem.getCategory()!=null) {
			oldFoodItem.setCategory(foodItem.getCategory());
		}
		/*
		 * FoodItem getFoodItem=findByItemName(foodItem.getItemName());
		 */		/*
		 * if(getFoodItem!=null) { throw new
		 * BadRequestException("fooditem is Already Present"); }
		 */
		if (isNameUpdated) {
	        FoodItem existingItemWithNewName = findByItemName(foodItem.getItemName());
	        if (existingItemWithNewName != null) {
	            throw new BadRequestException("Food item with the name '" + foodItem.getItemName() + "' already exists.");
	        }
	    }
		if(foodItem.getItemName()!=null) {
			oldFoodItem.setItemName(foodItem.getItemName());
		}
		FoodItem foodItem2=foodItemRepository.save(oldFoodItem);
		
		
		return foodItem2;
		
	}

	@Override
	public List<FoodItem> findAllFoodItems() {
		// TODO Auto-generated method stub
		
		return foodItemRepository.findAll();
	}

	@Override
	public FoodItem findByItemName(String itemName) {
		Optional<FoodItem> foodOptional = foodItemRepository.findByItemName(itemName);

		if(foodOptional.isPresent()){
			return  foodOptional.get();
		}else {
			return null;
		}
				
	}
	/*
	 * @Override public List<FoodItem> getFoodItemsByCategory(String category) {
	 * return foodItemRepository.findByCategoryOrderByName(category); }
	 */

	@Override
	public List<FoodItem> getFoodItemsByCategory(String category) {
	        return foodItemRepository.findByCategory(category);
	}
	
	
}

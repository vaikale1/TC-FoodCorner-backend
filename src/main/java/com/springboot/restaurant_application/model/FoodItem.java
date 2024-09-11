package com.springboot.restaurant_application.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="Restaurent_foodItem")
public class FoodItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item_id")
	private int itemId;
	@Column(name="item_name")
	private String itemName;
	@Column(name="description")
	private String description;
	@Column(name="price")
	private float price;
	@Column(name="category")
	private String category;
	@Lob
	@Column(name="itemImage")
	private String itemImage;
	@Override
	public String toString() {
		return "FoodItem [itemId=" + itemId + ", itemName=" + itemName + ", description=" + description + ", price="
				+ price + ", category=" + category + ", itemImage=" + itemImage + "]";
	}
	public FoodItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FoodItem(int itemId, String itemName, String description, float price, String category, String itemImage) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.category = category;
		this.itemImage = itemImage;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getItemImage() {
		return itemImage;
	}
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
	

}

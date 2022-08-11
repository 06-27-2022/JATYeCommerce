package com.jaty.models;

public class jatyProduct {

	/**
	 * Unique identifier for internal use.
	 */
	private int id;
	/**
	 * Reference to the jatyAccount id that owns an instance 
	 * of  jatyProduct.
	 */
	private int accountId;
	/**
	 * Primary key used to retrieve picture assigned to 
	 * jatyProduct.
	 */
	private String picture;
	/**
	 * Description of the jatyProduct including the name.
	 */
	private String description;
	/**
	 * The number of the jatyProduct available for purchase.
	 */
	private int stock;
	/**
	 * The price of the jatyProduct per unit in US dollars.
	 */
	private double price;
	
	//constructors for jatyProduct.
	
	public jatyProduct() {
		
	}
	/**
	 * Creates a complete jatyProduct object.
	 * @param id
	 * @param accountId
	 * @param picture
	 * @param description
	 * @param stock
	 * @param price
	 */
	public jatyProduct(int id, int accountId, String picture, String description, int stock, double price) {
		this.id=id;
		this.accountId=accountId;
		this.picture=picture;
		this.description=description;
		this.stock=stock;
		this.price=price;
	}
	//getter and setter methods for jatyProduct.
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}

package com.jaty.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="jatyProduct")
public class jatyProduct {

	/**
	 * Unique identifier for internal use.
	 */
	@Id
	@Column(name="id")
	@GeneratedValue(generator = "jatyProduct_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize=1, name="jatyProduct_id_seq")
	private int id;
	/**
	 * Reference to the jatyAccount id that owns an instance 
	 * of  jatyProduct.
	 */
	@ManyToOne
	@JoinColumn(name="accountID")
	private jatyAccount accountId;
	/**
	 * Primary key used to retrieve picture assigned to 
	 * jatyProduct.
	 */
	@Column(name="picture")
	private String picture;
	/**
	 * Description of the jatyProduct including the name.
	 */
	@Column(name="description")
	private String description;
	/**
	 * The number of the jatyProduct available for purchase.
	 */
	@Column(name="stock")
	private int stock;
	/**
	 * The price of the jatyProduct per unit in US dollars.
	 */
	@Column(name="price")
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
	public jatyProduct(int id, jatyAccount accountId, String picture, String description, int stock, double price) {
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
	public jatyAccount getAccountId() {
		return accountId;
	}
	public void setAccountId(jatyAccount accountId) {
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

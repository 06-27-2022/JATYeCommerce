package com.jaty.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="jatyproduct")
public class Product {

	/**
	 * Unique identifier for internal use.
	 */
	@Id
	@Column(name="id")
	@GeneratedValue(generator = "jatyproduct_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize=1, name="jatyproduct_id_seq")
	private int id;
	/**
	 * Reference to the jatyAccount id that owns an instance 
	 * of  jatyProduct.
	 */
	@ManyToOne
	@JoinColumn(name="accountid")
	private Account accountid;
	/**
	 * Primary key used to retrieve picture assigned to 
	 * jatyProduct.
	 */
	@Column(name="picture")
	private String picture;
	@Column(name="productname")
	private String name;
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
	
	@ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      })
	@JoinTable(name="jatyproducttotag",
		joinColumns = 			{@JoinColumn(name="productid", 	referencedColumnName="id")},
		inverseJoinColumns = 	{@JoinColumn(name="tagid", 		referencedColumnName="id")})
	private Set<Tag>tags=new HashSet<>();
	
	//constructors for jatyProduct.
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	public void addTag(Tag tag) {
		this.tags.add(tag);
		tag.getProducts().add(this);
	}
	public void removeTag(long tagId) {
	    Tag tag = this.tags.stream().filter(t -> t.getId() == tagId).findFirst().orElse(null);
	    if (tag != null) {
	      this.tags.remove(tag);
	      tag.getProducts().remove(this);
	    }
	}
	
	public Product() {
		
	}
	/**
	 * Creates a complete jatyProduct object.
	 * @param id
	 * @param accountId
	 * @param picture
	 * @param description
	 * @param stock
	 * @param price
	 * @param name
	 */
	public Product(int id, Account accountId, String picture, String description, int stock, double price, String name) {
		this.id=id;
		this.accountid=accountId;
		this.picture=picture;
		this.description=description;
		this.stock=stock;
		this.price=price;
		this.name=name;
	}
	//getter and setter methods for jatyProduct.
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Account getAccountId() {
		return accountid;
	}
	public void setAccountId(Account accountId) {
		this.accountid = accountId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	
}

package com.jaty.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="jatyreview")
public class Review {
	
	/**
	 * Unique identifier for internal use.
	 */
	@Id
	@Column(name="id")
	@GeneratedValue(generator = "review_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize=1, name="review_id_seq")
	private int id;
	/**
	 * Reference to the jatyAccount id that owns an instance 
	 * of  jatyReview.
	 */
	@OneToOne
	@JoinColumn(name="accountID")
	private Account accountId;
	/**
	 * Reference to the jatyProduct id that owns an instance 
	 * of  jatyReview.
	 */
	@ManyToOne
	@JoinColumn(name="productID")
	private Product productId;
	/**
	 * A 0 to 5 rating of the product the user received.
	 */
	@Column(name="rating")
	private int rating;
	/**
	 * A description of the product the user received.
	 */
	@Column(name="review")
	private String reviewBody;
	
	
	//constructors for jatyReview.
	public Review() {
		
	}
	
	/**
	 * Creates a complete jatyReview object.
	 * @param id
	 * @param accountId
	 * @param productId
	 * @param rating
	 * @param reviewBody
	 */
	public Review(int id, Account accountId, Product productId, int rating, String reviewBody) {
		this.id=id;
		this.accountId=accountId;
		this.productId=productId;
		this.rating=rating;
		this.reviewBody=reviewBody;
	}
	
	//getters and setters for jatyReview.
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Account getAccountId() {
		return accountId;
	}
	public void setAccountId(Account accountId) {
		this.accountId = accountId;
	}
	public Product getProductId() {
		return productId;
	}
	public void setProductId(Product productId) {
		this.productId = productId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReviewBody() {
		return reviewBody;
	}
	public void setReviewBody(String reviewBody) {
		this.reviewBody = reviewBody;
	}
	
	
}

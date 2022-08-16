package com.jaty.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

public class jatyReview {
	
	/**
	 * Unique identifier for internal use.
	 */
	@Id
	@Column(name="id")
	@GeneratedValue(generator = "jatyReview_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize=1, name="jatyReview_id_seq")
	private int id;
	/**
	 * Reference to the jatyAccount id that owns an instance 
	 * of  jatyReview.
	 */
	@OneToOne
	@JoinColumn(name="accountID")
	private jatyAccount accountId;
	/**
	 * Reference to the jatyProduct id that owns an instance 
	 * of  jatyReview.
	 */
	@ManyToOne
	@JoinColumn(name="productID")
	private jatyProduct productId;
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
	public jatyReview() {
		
	}
	
	/**
	 * Creates a complete jatyReview object.
	 * @param id
	 * @param accountId
	 * @param productId
	 * @param rating
	 * @param reviewBody
	 */
	public jatyReview(int id, jatyAccount accountId, jatyProduct productId, int rating, String reviewBody) {
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
	public jatyAccount getAccountId() {
		return accountId;
	}
	public void setAccountId(jatyAccount accountId) {
		this.accountId = accountId;
	}
	public jatyProduct getProductId() {
		return productId;
	}
	public void setProductId(jatyProduct productId) {
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

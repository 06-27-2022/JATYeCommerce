package com.jaty.models;

public class jatyReview {
	
	/**
	 * Unique identifier for internal use.
	 */
	private int id;
	/**
	 * Reference to the jatyAccount id that owns an instance 
	 * of  jatyReview.
	 */
	private int accountId;
	/**
	 * Reference to the jatyProduct id that owns an instance 
	 * of  jatyReview.
	 */
	private int productId;
	/**
	 * A 0 to 5 rating of the product the user received.
	 */
	private int rating;
	/**
	 * A description of the product the user received.
	 */
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
	public jatyReview(int id, int accountId, int productId, int rating, String reviewBody) {
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
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
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

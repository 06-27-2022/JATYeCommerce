package com.jaty.models;

public class jatyTag {
	
	/**
	 * Unique identifier for internal use.
	 */
	private int id;
	/**
	 * A descriptor/category that groups a product with other
	 * products.
	 */
	private String tag;
	/**
	 * Identifies if a jatyTag will ban a product from 
	 * JATY and make it inaccessible on our store front.
	 */
	private boolean ban;
	
	//constructors for jatyTag.
	public jatyTag() {
		
	}
	
	/**
	 * Creates a complete jatyTag object.
	 * @param id
	 * @param tag
	 * @param ban
	 */
	public jatyTag(int id, String tag, boolean ban) {
		this.setId(id);
		this.setTag(tag);
		this.setBan(ban);
	}

	//getters and setters for jatyTag.
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isBan() {
		return ban;
	}

	public void setBan(boolean ban) {
		this.ban = ban;
	}
}

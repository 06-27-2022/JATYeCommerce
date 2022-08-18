package com.jaty.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class jatyTag {
	
	/**
	 * Unique identifier for internal use.
	 */
	@Id
	@Column(name="id")
	@GeneratedValue(generator = "jatyTag_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize=1, name="jatyTag_id_seq")
	private int id;
	/**
	 * A descriptor/category that groups a product with other
	 * products.
	 */
	@Column(name="tag")
	private String tag;
	/**
	 * Identifies if a jatyTag will ban a product from 
	 * JATY and make it inaccessible on our store front.
	 */
	@Column(name="ban")
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

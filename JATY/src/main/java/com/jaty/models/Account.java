package com.jaty.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * This class models an account for the JATY eCommerce
 * store. It is meant to handle transfer of data 
 * between the client and the database.
 * @author 1jestor1
 *
 */
@Entity
@Table(name = "jatyaccount")
public class Account {

	/**
	 * Unique identifier used to reference a specific
	 * account in products, the wallet and reviews owned
	 * by the account.
	 */
	@Id
	@Column(name="id")
	@GeneratedValue(generator="jatyaccount_id_seq",strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize=1, name="jatyaccount_id_seq")
	private int id;
	/**
	 * Unique username used for account login/creation,
	 * and display name for product listing.
	 */
	@Column(name="username")
	private String username;
	/**
	 * Password used to access a specific account for a
	 * linked username during login.
	 */
	@Column(name="password")
	private String password;
	/**
	 * Role assigned to account. Currently implemented
	 * roles:
	 * + default: access buying listed products and selling
	 *            products on the account.
	 * + moderator: in addition to default account features,
	 *             this account can flag products for bans.
	 */
	@Column(name="role")
	private String role;
	/**
	 * The city the owner of the account sends their stock from.
	 */
	@Column(name="city")
	private String city;
	/**
	 * The state the owner of the account sends their stock from.
	 */
	@Column(name="state")
	private String state;
	
	//constructors for jatyAccount
	
	public Account() {
		
	}
	
	/**
	 * Creates a complete jatyAccount object.
	 * @param id
	 * @param username
	 * @param password
	 * @param role
	 * @param city
	 * @param state
	 */
	public Account(int id, String username, String password, String role, String city, String state) {
		this.id=id;
		this.username=username;
		this.password=password;
		this.role=role;
		this.city =  city;
		this.state = state;
	}
	
	/**
	 * Creates jatyAccount object with only a username
	 * and password. It is intended for account login 
	 * and creation.
	 * @param username
	 * @param password
	 */
	public Account(String username, String password) {
		this.username=username;
		this.password=password;
	}
	
	//getter and setter methods for jatyAccount
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}

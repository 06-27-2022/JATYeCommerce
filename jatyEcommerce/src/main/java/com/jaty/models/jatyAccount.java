package com.jaty.models;

/**
 * This class models an account for the JATY eCommerce
 * store. It is meant to handle transfer of data 
 * between the client and the database.
 * @author 1jestor1
 *
 */
public class jatyAccount {

	/**
	 * Unique identifier used to reference a specific
	 * account in products, the wallet and reviews owned
	 * by the account.
	 */
	private int id;
	/**
	 * Unique username used for account login/creation,
	 * and display name for product listing.
	 */
	private String username;
	/**
	 * Password used to access a specific account for a
	 * linked username during login.
	 */
	private String password;
	/**
	 * Role assigned to account. Currently implemented
	 * roles:
	 * + default: access buying listed products and selling
	 *            products on the account.
	 * + moderator: in addition to default account features,
	 *             this account can flag products for bans.
	 */
	private String role;
	
	//constructors for jatyAccount
	
	public jatyAccount() {
		
	}
	
	/**
	 * Creates a complete jatyAccount object.
	 * @param id
	 * @param username
	 * @param password
	 * @param role
	 */
	public jatyAccount(int id, String username, String password, String role) {
		this.id=id;
		this.username=username;
		this.password=password;
		this.role=role;
	}
	
	/**
	 * Creates jatyAccount object with only a username
	 * and password. It is intended for account login 
	 * and creation.
	 * @param username
	 * @param password
	 */
	public jatyAccount(String username, String password) {
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
	
}

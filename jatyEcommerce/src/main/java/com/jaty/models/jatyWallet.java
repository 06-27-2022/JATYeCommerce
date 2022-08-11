package com.jaty.models;

public class jatyWallet {
	
	/**
	 * Unique identifier for internal use.
	 */
	private int id;
	/**
	 * Reference to the jatyAccount id that owns an instance 
	 * of  jatyWallet.
	 */
	private int accountId;
	/**
	 * How much money is available for use in an jatyAccount.
	 */
	private double balance;
	
	//constructors for jatyWallet
	
	public jatyWallet() {
		
	}
	/**
	 * Creates a complete jatyWallet object.
	 * @param id
	 * @param accountId
	 * @param balance
	 */
	public jatyWallet(int id, int accountId, double balance) {
		this.setId(id);
		this.setAccountId(accountId);
		this.setBalance(balance);
	}
	/**
	 * Creates a jatyWallet object with only accountId and Balance.
	 * Intended for the creation of wallet after a jatyAccount
	 * has been created.
	 * @param accountId
	 * @param balance
	 */
	public jatyWallet(int accountId, double balance) {
		this.accountId=accountId;
		this.balance=balance;
	}
	//methods for jatyWallet
	
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
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	
}

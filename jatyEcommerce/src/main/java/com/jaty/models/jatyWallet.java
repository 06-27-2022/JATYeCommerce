package com.jaty.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

public class jatyWallet {
	
	/**
	 * Unique identifier for internal use.
	 */
	@Id
	@Column(name="id")
	@GeneratedValue(generator = "jatyWallet_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize=1, name="jatyWallet_id_seq")
	private int id;
	/**
	 * Reference to the jatyAccount id that owns an instance 
	 * of  jatyWallet.
	 */
	@ManyToOne
	@JoinColumn(name="accountID")
	private jatyAccount accountId;
	/**
	 * How much money is available for use in an jatyAccount.
	 */
	@Column(name="balance")
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
	public jatyWallet(int id, jatyAccount accountId, double balance) {
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
	public jatyWallet(jatyAccount accountId, double balance) {
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
	public jatyAccount getAccountId() {
		return accountId;
	}
	public void setAccountId(jatyAccount accountId) {
		this.accountId = accountId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	
}

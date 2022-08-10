package com.jaty.account;

import com.jaty.product.ProductTable;
import com.jaty.wallet.Wallet;

public class AccountLocal implements Account{

	private String name;
	private String password;
	private String role;
	private Wallet wallet;
	private ProductTable inventory;
	
	public AccountLocal() {
		this.name=null;
		this.password=null;
		this.role=null;
		this.wallet=null;
		this.inventory=null;
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name=name;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password=password;
	}

	@Override
	public String getRole() {
		return role;
	}

	@Override
	public void setRole(String role) {
		if(role.equalsIgnoreCase("Manager"))
			this.role="Manager";
		this.role="Global";
	}

	@Override
	public Wallet getWallet() {
		return wallet;
	}

	@Override
	public void setWallet(Wallet wallet) {
		this.wallet=wallet;
	}

	@Override
	public ProductTable getInventory() {
		return inventory;
	}

	@Override
	public void setInventory(ProductTable inventory) {
		this.inventory=inventory;
	}

}

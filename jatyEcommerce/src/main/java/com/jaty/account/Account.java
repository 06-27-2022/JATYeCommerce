package com.jaty.account;

import com.jaty.product.ProductTable;
import com.jaty.wallet.Wallet;

public interface Account {
	
	public abstract int getID();
	
	public abstract String getName();
	public abstract void setName(String name);
	
	public abstract String getPassword();
	public abstract void setPassword(String password);
	
	public abstract String getRole();
	public abstract void setRole(String role);

	public abstract Wallet getWallet();		
	public abstract void setWallet(Wallet wallet);
	
	public abstract ProductTable getInventory();		
	public abstract void setInventory(ProductTable inventory);
	
}

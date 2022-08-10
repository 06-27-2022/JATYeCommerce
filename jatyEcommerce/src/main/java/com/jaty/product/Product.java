package com.jaty.product;

import java.io.File;

import com.jaty.account.Account;
import com.jaty.tag.Tag;

public interface Product {
	public int getID();
	
	public abstract File getPicture();
	public abstract void setPicture(File file);
	
	public abstract String getDescription();
	public abstract void setDescription(String description);
	
	public abstract int getStock();
	public abstract void setStock(int stock);
	
	public abstract double getPrice();
	public abstract void setPrice();
	
	public abstract Account getSeller();
	public abstract void setSeller(Account account);
	
	public abstract Tag[] getTags();
	public abstract void addTags(Tag[]tags);
}

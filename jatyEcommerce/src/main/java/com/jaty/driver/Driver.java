package com.jaty.driver;

import com.jaty.account.Account;
import com.jaty.account.AccountLocal;
import com.jaty.account.AccountTable;
import com.revature.jdbc.Table;

public class Driver {

	public static void main(String[] args) {
		Table<Account>table=new AccountTable();
		Account acc=new AccountLocal();
		acc.setName("test1");
		acc.setPassword("test2");
//		table.add(local);
		
		acc=table.get(1);
		acc.setRole("manager");
		System.out.println(acc.getWallet());
		
		
	}

}

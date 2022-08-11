package com.jaty.account;

import com.jaty.product.ProductTable;
import com.jaty.wallet.Wallet;
import com.revature.jdbc.ConnectionUtil;

public class AccountRemote implements Account{

	private final int id;
	private final String tablename="jatyaccount";
	
	public AccountRemote(int id){
		this.id=id;
	}
	
	@Override
	public int getID() {
		return id;
	}

	@Override
	public String getName() {
		String SQL="select username from "+tablename+" where id=?";
		return (String) ConnectionUtil.stmtExecuteQuery(SQL, getID());
	}

	@Override
	public void setName(String name) {
		final String SQL="update "+tablename+" set username=? where id=?";
		ConnectionUtil.stmtExecute(SQL,name,id);	
	}

	@Override
	public String getPassword() {
		String SQL="select password from "+tablename+" where id=?";
		return (String) ConnectionUtil.stmtExecuteQuery(SQL, id);
	}

	@Override
	public void setPassword(String password) {
		final String SQL="update "+tablename+" set password=? where id=?";
		ConnectionUtil.stmtExecute(SQL,password,id);	
	}

	@Override
	public String getRole() {
		String SQL="select role from "+tablename+" where id=?";
		return (String) ConnectionUtil.stmtExecuteQuery(SQL, id);
	}

	@Override
	public void setRole(String role) {
		if(role!=null&&role.equalsIgnoreCase("Manager"))
			role="Manager";
		else 
			role="Global";
		final String SQL="update "+tablename+" set role=? where id=?";
		ConnectionUtil.stmtExecute(SQL,role,id);	
	}

	@Override
	public Wallet getWallet() {
		// TODO Auto-generated method stub
		String SQL="select wallet from "+tablename+" where id=?";
		Object o=ConnectionUtil.stmtExecuteQuery(SQL, id);
		if(o==null)
			return null;
		else
			return null;
	}

	@Override
	public void setWallet(Wallet wallet) {
		// TODO Auto-generated method stub
	}

	@Override
	public ProductTable getInventory() {
		// TODO Auto-generated method stub
		String SQL="select inventory from "+tablename+" where id=?";
		Object o=ConnectionUtil.stmtExecuteQuery(SQL, id);
		if(o==null)
			return null;
		else
			return null;
	}

	@Override
	public void setInventory(ProductTable inventory) {
		// TODO Auto-generated method stub
		
	}

}

package com.jaty.account;

import java.util.LinkedList;
import java.util.List;

import com.revature.jdbc.ConnectionUtil;
import com.revature.jdbc.Table;

public class AccountTable implements Table<Account>{

	private final String tablename="jatyaccount";
	
	@Override
	public Account get(int id) {
		String SQL="select * from "+tablename+" where id=?";
		Object[]query=ConnectionUtil.stmtExecuteQuery1D(SQL, id);

		return arrayToAccount(query);
	}

	@Override
	public Account[] getSome(int limit, int offset) {
		String SQL="select * from "+tablename+" order by id asc limit ? offset ?";
		Object[][]query=ConnectionUtil.stmtExecuteQuery2D(SQL, limit,offset);

		Account[]accounts=new Account[limit];
		for(int i=0;i<limit;i++)
			accounts[i]=arrayToAccount(query[i]);
		
		return accounts;
	}

	@Override
	public Account[] getAll() {
		String SQL="select * from "+tablename+" order by id";
		Object[][]query=ConnectionUtil.stmtExecuteQuery2D(SQL);
		
		List<Account>accounts=new LinkedList<Account>();
		for(int i=0;i<query.length;i++)
			accounts.add(arrayToAccount(query[i]));

		return accounts.toArray(new Account[0]);
	}

	private Account arrayToAccount(Object[]args) {
		Account acc = new AccountRemote((int) args[0]);
		acc.setName((String) args[1]);
		return acc;
	}
	
	@Override
	public void add(Account account) {
		String SQL="insert into "+tablename+" (username,password)values(?,?)";
		ConnectionUtil.stmtExecute(SQL, account.getName(),account.getPassword());
	}

	@Override
	public int size() {
		String SQL="select count(*) from "+tablename;
		return (int) ConnectionUtil.stmtExecuteQuery(SQL);
	}

	@Override
	public boolean isEmpty() {
		if(size()==0)
			return true;
		return false;
	}

}

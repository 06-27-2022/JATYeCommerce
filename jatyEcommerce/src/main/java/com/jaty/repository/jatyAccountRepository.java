package com.jaty.repository;

import org.springframework.stereotype.Repository;

import com.jaty.jdbc.ConnectionUtil;
import com.jaty.models.jatyAccount;

@Repository("jatyAccountRepository")
public class jatyAccountRepository implements jatyRepository<jatyAccount>{

	private jatyAccount arrToAccount(Object[]arr) {
		jatyAccount acc = new jatyAccount();
		if(arr.length==0)
			return acc;
		acc.setId((int) arr[0]);
		acc.setUsername((String)arr[1]);
		acc.setPassword((String)arr[2]);
		acc.setRole((String)arr[3]);
		acc.setCity((String)arr[4]);
		acc.setState((String)arr[5]);
		return acc;
	}
	
	@Override
	public jatyAccount get(int id) {
		String SQL="select id,username,password,role,city,state from jatyAccount where id=?";
		Object[]arr=ConnectionUtil.stmtExecuteQuery1D(SQL, id);
		return arrToAccount(arr);
	}

	@Override
	public jatyAccount get(jatyAccount obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public jatyAccount[] getSome(int limit, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public jatyAccount[] getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(jatyAccount obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(jatyAccount obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(jatyAccount obj) {
		// TODO Auto-generated method stub
		
	}

}

package com.jaty.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaty.models.Account;
import com.jaty.repository.AccountRepository;

@Service("jatyAccountService")
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public AccountService() {}
	
	public Account getAccount(int id) {
		return this.accountRepository.findById(id);
	}
	public void createAccount(Account account) {
		this.accountRepository.save(account);
	}
	public List<Account> findAllByOrderById(){
		return this.accountRepository.findAllByOrderById();
	}
	
	public Account getAccountFromLogIn(String username, String password) {
		return this.accountRepository.findByUsernameAndPassword(username, password);
	}
}

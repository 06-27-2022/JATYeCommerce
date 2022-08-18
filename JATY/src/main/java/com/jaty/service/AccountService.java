package com.jaty.service;

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
}

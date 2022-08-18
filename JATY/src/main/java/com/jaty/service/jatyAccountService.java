package com.jaty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaty.models.jatyAccount;
import com.jaty.repository.jatyAccountRepository;

@Service("jatyAccountService")
public class jatyAccountService {
	
	@Autowired
	private jatyAccountRepository accountRepository;
	
	public jatyAccountService() {}
	
	public jatyAccount getAccount(int id) {
		return this.accountRepository.getReferenceById(id);
	}
	
}

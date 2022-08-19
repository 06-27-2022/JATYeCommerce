package com.jaty.controller;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaty.models.Account;
import com.jaty.service.AccountService;

@RestController("jatyAccountController")
@RequestMapping(path ="/account")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	Logger log;
	
	@RequestMapping(path="/test")
	public String test() {
		System.out.println("test endpoint");
		log.trace("testing logger bean");
		return "test";
	}
	@RequestMapping(path="/get")
	public Account get(@RequestParam int id) {
		return this.accountService.getAccount(id);
	}
	@RequestMapping(path="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Account account) {
		this.accountService.createAccount(account);
	}

	
}

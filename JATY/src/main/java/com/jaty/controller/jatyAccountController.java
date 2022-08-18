package com.jaty.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaty.models.jatyAccount;
import com.jaty.service.jatyAccountService;

@RestController("jatyAccountController")
@RequestMapping(path ="/account")
public class jatyAccountController {
	
	@Autowired
	jatyAccountService accountService;
	
	@RequestMapping(path="/test")
	public String test() {
		return "test";
	}
	@RequestMapping(path="/get")
	public jatyAccount get(@RequestParam int id) {
		return this.accountService.getAccount(id);
	}

	
}

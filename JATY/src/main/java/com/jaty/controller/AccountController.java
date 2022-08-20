package com.jaty.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaty.models.Account;
import com.jaty.models.Wallet;
import com.jaty.service.AccountService;
import com.jaty.service.WalletService;

@RestController("jatyAccountController")
@RequestMapping(path ="/account")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	WalletService walletService;
	
	@Autowired
	Logger log;
	
	//needs to be removed before roll out
	@RequestMapping(path="/test")
	public String test() {
		System.out.println("test endpoint");
		log.trace("testing logger bean");
		return "test";
	}
	//needs to be removed before roll out
	@RequestMapping(path="/get")
	public Account get(@RequestParam int id) {
		return this.accountService.getAccount(id);
	}
	//need to be removed before roll out
	@RequestMapping(path="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Account account) {
		this.accountService.createAccount(account);
	}
	//needs to be removed before roll out
	@RequestMapping(path="/listall")
	public List<Account> findAllByOrderById(){
		return this.accountService.findAllByOrderById();
	}
	//needs to be removed before roll out
	@RequestMapping(path="/checksession")
	public String checkSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String s = "session is null";
		if(session != null) {
			s = "account id is " + session.getAttribute("accountId");
		}
		return s;
	}
	
	
	@RequestMapping(path="/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String  logIn(@RequestBody Account account, HttpServletRequest request) {
		Account loginAttempt = accountService.getAccountFromLogIn(account.getUsername(), account.getPassword());
		if(loginAttempt==null) {
			return "bad-login";
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("accountId", loginAttempt.getId());
			session.setAttribute("role", loginAttempt.getRole());
			return "good-login";
		}
	}
	@RequestMapping(path="/logout")
	public String logOut(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) session.invalidate();
		return "logged-out";
	}
	@RequestMapping(path="/new",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String newAccount(@RequestBody Account account, HttpServletRequest request) {
		Account accountSearch = accountService.getAccountFromLogIn(account.getUsername(), account.getPassword());
		if(accountSearch==null) {
			account.setRole("default");
			accountService.createAccount(account);
			accountSearch = accountService.getAccountFromLogIn(account.getUsername(), account.getPassword());
			HttpSession session = request.getSession();
			session.setAttribute("accountId", accountSearch.getId());
			session.setAttribute("role", accountSearch.getRole());
			//logic to create a wallet
			Wallet newWallet = new Wallet(accountSearch, 0.00);
			walletService.save(newWallet);
			return "new-user-created with id = "+ session.getAttribute("accountId");
		}else {
			return "username-already-exists";
		}
	}
}

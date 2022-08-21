package com.jaty.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

@RestController("jatyWalletController")
@RequestMapping(path="/wallet")
public class WalletController {
	@Autowired
	WalletService walletService;
	
	@Autowired
	AccountService accountService;
	@RequestMapping(path="/get")
	public Wallet get(@RequestParam int id) {
		return this.walletService.getWallet(id);
	}
	
	@RequestMapping(path="/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Wallet wallet) {
		this.walletService.save(wallet);
	}
	@RequestMapping(path="/account")
	public Object checkWallet(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
		Account accountSearch = accountService.getAccount((int) session.getAttribute("accountId"));	
		return walletService.getWalletByAccount(accountSearch);
		}
			
		return null;
	}
}

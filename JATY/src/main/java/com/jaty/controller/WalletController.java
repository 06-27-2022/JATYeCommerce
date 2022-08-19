package com.jaty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaty.models.Wallet;
import com.jaty.service.WalletService;

@RestController("jatyWalletController")
@RequestMapping(path="/wallet")
public class WalletController {
	@Autowired
	WalletService walletService;
		
	@RequestMapping(path="/get")
	public Wallet get(@RequestParam int id) {
		return this.walletService.getWallet(id);
	}
	
	@RequestMapping(path="/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Wallet wallet) {
		this.walletService.save(wallet);
	}

}

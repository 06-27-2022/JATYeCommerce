package com.jaty.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaty.models.Account;
import com.jaty.models.Wallet;
import com.jaty.repository.AccountRepository;
import com.jaty.repository.WalletRepository;

@Service("jatyWalletService")
public class WalletService {

	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	public WalletService() {}
	
	public Wallet getWallet(int id) {
		return this.walletRepository.findById(id);
	}
	
	public void save(Wallet wallet) {
		this.walletRepository.save(wallet);
	}
	
	public Wallet getWalletByAccount(int accountid) {
		if(accountid != 0) {
		Account accountSearch = this.accountRepository.findById(accountid);	
		return this.walletRepository.findByAccountId(accountSearch);
		}
		return null;
		
	}
	
	public String editWalletBalance(double edit, int accountid) {
		Wallet targetWallet = getWalletByAccount(accountid);
		targetWallet.setBalance(targetWallet.getBalance() + edit);
		this.walletRepository.save(targetWallet);
		return "new-wallet-balance: " + targetWallet.getBalance();
	}
}

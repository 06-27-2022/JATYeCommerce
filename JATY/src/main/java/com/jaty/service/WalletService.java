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
	
	public Wallet getWalletByAccount(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
		session = request.getSession();
		Account accountSearch = this.accountRepository.findById((int) session.getAttribute("accountId"));	
		return this.walletRepository.findByAccountId(accountSearch);
		}
		return null;
		
	}
}

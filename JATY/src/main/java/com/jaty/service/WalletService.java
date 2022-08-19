package com.jaty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaty.models.Tag;
import com.jaty.models.Wallet;
import com.jaty.repository.WalletRepository;

@Service("jatyWalletService")
public class WalletService {

	@Autowired
	private WalletRepository walletRepository;

	public WalletService() {}
	
	public Wallet getWallet(int id) {
		return this.walletRepository.findById(id);
	}
	
	public void save(Wallet wallet) {
		this.walletRepository.save(wallet);
	}
	
}

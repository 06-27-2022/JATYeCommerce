package com.jaty.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaty.models.Account;
import com.jaty.models.Wallet;
import com.jaty.repository.AccountRepository;

@Service("jatyAccountService")
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private WalletService walletService;
	
	public AccountService() {}
	
	public Account getAccount(int id) {
		return this.accountRepository.findById(id);
	}
	public void createAccount(Account account) {
		this.accountRepository.save(account);
	}
	public List<Account> findAllByOrderById(){
		return this.accountRepository.findAllByOrderById();
	}
	
	
	
	public String accountLogin(Account account, HttpServletRequest request) {
		Account loginAttempt = this.accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
		if(loginAttempt==null) {
			return "bad-login";
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("accountId", loginAttempt.getId());
			session.setAttribute("role", loginAttempt.getRole());
			return "good-login";
		}		
	}
	
	public String accountLogout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) session.invalidate();
		return "logged-out";
	}
	
	public String clientCreateAccount(Account account, HttpServletRequest request) {
		Account accountSearch = this.accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
		if(accountSearch==null) {
			account.setRole("default");
			this.accountRepository.save(account);
			accountSearch = this.accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
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

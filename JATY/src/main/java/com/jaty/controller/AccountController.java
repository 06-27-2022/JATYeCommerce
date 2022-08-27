package com.jaty.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaty.models.Account;
import com.jaty.service.AccountService;

@RestController("jatyAccountController")
@RequestMapping(path ="/account")
@CrossOrigin
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
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
	
	/**
	 * Retrieves a json containing login necessary information, checks it
	 * against database records to determine access and gives client a HttpSession for further 
	 * site navigation.
	 * @param account object should have both the username and password as strings in the json.
	 * @param request holds the HttpSession.
	 * @return HttpSession with accountId and accountRole attributes.
	 */
	@RequestMapping(path="/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String  logIn(@RequestBody Account account) {
		return this.accountService.accountLogin(account);
	}
	
	/**
	 * Removes all information from the HttpSession to disable access.
	 * @param request
	 * @return an empty HttpSession
	 */
//	@RequestMapping(path="/logout")
//	public String logOut(HttpServletRequest request) {
//		return this.accountService.accountLogout(request);
//	}
//	
	/**
	 * Retrieves a json containing account necessary information, checks it
	 * against database records to determine validity, modifies the database appropriately in both
	 * the jatyAccount & jatyWallet tables and provides an HttpSession similar to the logIn end-point. 
	 * @param account object should have both the username, password, city and state as strings in the json.
	 * @param request holds the HttpSession.
	 * @return HttpSession with accountId and accountRole attributes.
	 */
	@RequestMapping(path="/new",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String newAccount(@RequestBody Account account) {
		return this.accountService.clientCreateAccount(account);
	}
}

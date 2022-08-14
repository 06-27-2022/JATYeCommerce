package com.jaty.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaty.jdbc.ConnectionUtil;
import com.jaty.models.jatyAccount;
import com.jaty.service.jatyAccountService;

@RestController("jatyAccountController")
@RequestMapping(path ="/account")
public class jatyAccountController {

	@Autowired
	jatyAccountService accountService;
	
	@RequestMapping(path="/test")
	public List<Object> test() {
//		List<String>list=Arrays.asList(new String[]{"1","2","3","asdf","qwer"});
//		return list;
		
		String SQL="select * from jatyAccount where id=?";
		Object[]acc=ConnectionUtil.stmtExecuteQuery1D(SQL, 2);
//		System.out.println("acc="+Arrays.asList(acc));
		return Arrays.asList(acc);
	}
	
	@RequestMapping(path="/get")
	public jatyAccount get(@RequestParam int id) {
		return this.accountService.getAccount(id);
	}
}

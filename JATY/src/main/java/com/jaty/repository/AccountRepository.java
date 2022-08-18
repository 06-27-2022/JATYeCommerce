package com.jaty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaty.models.Account;

@Repository("jatyAccountRepository")
public interface AccountRepository extends JpaRepository<Account, Integer>{

	<S extends Account> S save(S entity);
	
	Account findById(int id);
}

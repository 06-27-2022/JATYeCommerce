package com.jaty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaty.models.Wallet;

@Repository("jatyWalletRepository")
public interface WalletRepository extends JpaRepository<Wallet, Integer>{
	<S extends Wallet> S save(S entity);

	Wallet findById(int id);
}

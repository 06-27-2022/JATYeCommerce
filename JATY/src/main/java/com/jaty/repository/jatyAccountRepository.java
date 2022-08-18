package com.jaty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaty.models.jatyAccount;

@Repository("jatyAccountRepository")
public interface jatyAccountRepository extends JpaRepository<jatyAccount, Integer>{

}

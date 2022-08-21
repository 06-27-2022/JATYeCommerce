package com.jaty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaty.models.Account;
import com.jaty.models.Product;

@Repository("jatyProductRepository")
public interface ProductRepository extends JpaRepository<Product, Integer>{
	<S extends Product> S save(S entity);
	
	Product findById(int id);
	
	List<Product> findByNameStartingWith(String productname);
	
	List<Product> findDistinctByTagsTagIn(List<String>tagnames);
	
	List<Product> findByAccountid(Account account);

}
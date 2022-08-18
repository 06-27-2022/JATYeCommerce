package com.jaty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaty.models.Product;

@Repository("jatyProductRepository")
public interface ProductRepository extends JpaRepository<Product, Integer>{
	<S extends Product> S save(S entity);
	
	Product findById(int id);
}

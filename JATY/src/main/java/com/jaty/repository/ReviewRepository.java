package com.jaty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaty.models.Account;
import com.jaty.models.Product;
import com.jaty.models.Review;

@Repository("jatyReviewRepository")
public interface ReviewRepository extends JpaRepository<Review, Integer>{
	<S extends Review> S save (S entity);
	
	Review findById(int id);
	
	List <Review> findByProductId(Product product);
	
	List <Review> findByProductIdAndAccountId(Product productid, Account accountid);
}

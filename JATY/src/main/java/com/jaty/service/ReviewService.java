package com.jaty.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaty.models.Account;
import com.jaty.models.Product;
import com.jaty.models.Review;
import com.jaty.repository.AccountRepository;
import com.jaty.repository.ProductRepository;
import com.jaty.repository.ReviewRepository;

@Service("jatyReviewService")
public class ReviewService {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	public ReviewService() {}
	
	public Review getReview(int id) {
		return this.reviewRepository.findById(id);
	}
	
	public void save(Review review) {
		this.reviewRepository.save(review);
	}
	
	public String createReviewForProduct(int productid, Review review, int accountid) {
		if(accountid != 0) {
			Account reviewer = this.accountRepository.findById(accountid);
			Product target = this.productRepository.findById(productid);
			if(this.reviewRepository.findByProductIdAndAccountId(target, reviewer)==null && target.getAccountId() != reviewer) {
				review.setProductId(target);
				review.setAccountId(reviewer);
				save(review);
				return "review-created-for: "+review.getProductId().getName()+" by "+review.getAccountId().getUsername();
			}
			return "can-not-add-review-to-this-product";
		}
		return "not-loged-in";
	}
	
	public List<Review> findAllReviewsByProductId(int productid){
		return this.reviewRepository.findByProductId(this.productRepository.findById(productid));
	}
}

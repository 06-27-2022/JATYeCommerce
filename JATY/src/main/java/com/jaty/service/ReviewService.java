package com.jaty.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public String createReviewForProduct(int productid, Review review, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session != null) {
			review.setProductId(this.productRepository.findById(productid));
			review.setAccountId(this.accountRepository.findById((int)session.getAttribute("accountId")));
			save(review);
			return "review-created-for:"+review.getProductId().getName()+" by "+review.getAccountId().getUsername();			
		}
		return "not-loged-in";
	}
}

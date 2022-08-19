package com.jaty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaty.models.Review;
import com.jaty.repository.ReviewRepository;

@Service("jatyReviewService")
public class ReviewService {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	public ReviewService() {}
	
	public Review getReview(int id) {
		return this.reviewRepository.findById(id);
	}
	
	public void save(Review review) {
		this.reviewRepository.save(review);
	}
	
}

package com.jaty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaty.models.Review;
import com.jaty.service.ReviewService;

@RestController("jatyReviewController")
@RequestMapping(path="/review")
public class ReviewController {
	@Autowired
	ReviewService reviewService;
		
	@RequestMapping(path="/get")
	public Review get(@RequestParam int id) {
		return this.reviewService.getReview(id);
	}
	
	@RequestMapping(path="/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Review review) {
		this.reviewService.save(review);
	}

}

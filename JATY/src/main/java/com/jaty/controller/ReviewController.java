package com.jaty.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(path="/{productid}/add", consumes=MediaType.APPLICATION_JSON_VALUE)
	public String addReviewToProduct(@PathVariable int productid, @RequestBody Review review, HttpServletRequest request) {
		return this.reviewService.createReviewForProduct(productid, review, request);
	}
	
	@RequestMapping(path="/{productid}/seeall")
	public List<Review> seeAllReviewsForProduct(@PathVariable int productid){
		return this.reviewService.findAllReviewsByProductId(productid);
	}
}

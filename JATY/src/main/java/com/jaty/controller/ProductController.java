package com.jaty.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaty.models.Product;
import com.jaty.service.ProductService;

@RestController("jatyProductController")
@RequestMapping(path="/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	Logger log;
	
	@RequestMapping(path="/test", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object test(@RequestBody Object obj) {
		System.out.println("test endpoint "+obj);
		log.trace("testing logger bean");
		return obj;
	}
	@RequestMapping(path="/get")
	public Product get(@RequestParam int id) {
		return this.productService.getProduct(id);
	}
	
	@RequestMapping(path="/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody Product product) {
		this.productService.saveProduct(product);
	}

}

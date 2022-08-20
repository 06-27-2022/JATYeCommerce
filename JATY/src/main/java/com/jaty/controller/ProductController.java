package com.jaty.controller;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaty.models.Account;
import com.jaty.models.Product;
import com.jaty.models.Tag;
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
	@RequestMapping(path="/search/id")
	public Product getByID(@RequestParam int id) {
		return this.productService.getProductById(id);
	}
	@RequestMapping(path="/search/tag")
	public List<Product> getByTag(@RequestBody Tag tag) {
		return this.productService.getProductByTags(tag);
	}
	@RequestMapping(path="/search/tags")
	public List<Product> getByTag(@RequestBody List<Tag> tag) {
		return this.productService.getProductByTagsIn(tag);
	}
	
	/**
	 * Will search all products with tag strings in the provided list. 
	 * The products must have all the tags provided in in order to be returned.
	 * @param tagnames a list of strings such as [asdf1,asdf2] 
	 * @return a list of products with no duplicates with all requested tags
	 */
	@RequestMapping(path="/search/tagnames")
	public List<Product> getByTagName(@RequestBody List<String> tagnames) {
		List<Product>allproducts=this.productService.getProductByTagsTagIn(tagnames);
		Set<Integer>duplicates = new HashSet<Integer>();
		List<Product>products = new LinkedList<Product>();
		for(Product p:allproducts) {
			if(duplicates.contains(p.getId()))
				continue;
			else{
				int count=0;
				for(Tag t:p.getTags()) 
					if(tagnames.contains(t.getTag()))
						count++;
				if(count==tagnames.size())
					products.add(p);
				duplicates.add(p.getId());
			}
		}
		return products;
	}

	@RequestMapping(path="/ban")
	public boolean getBanStatus(@RequestBody int id) {
		//TODO figure out how to get banned status of product's tags 
		return false;
	}
	
	/**
	 * Will provide all products listed under the provided account
	 * @param account the account whose products we are viewing
	 * @return a list of products who belong to the inputted account
	 */
	@RequestMapping(path="/search/account")
	public List<Product> getByAccount(@RequestBody Account account) {
		return this.productService.getProductByAccount(account);
	}
	
	/**
	 * Adds the product to the database.
	 * AccountId is currently manually included with the inputed json
	 * will need to leech off the account's httpsession for the accountid
	 * Currently is incapable of being passed with its tags. 
	 * @param product A Product Object.
	 */
	@RequestMapping(path="/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody Product product) {
		this.productService.saveProduct(product);
	}

}

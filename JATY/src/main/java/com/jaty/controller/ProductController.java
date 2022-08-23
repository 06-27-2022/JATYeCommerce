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

import com.jaty.models.Account;
import com.jaty.models.Product;
import com.jaty.service.ProductService;

/**
 * check the modules for json representations of the objects
 * @author tomh0
 *
 */
@RestController("jatyProductController")
@RequestMapping(path="/product")
public class ProductController {

	@Autowired
	ProductService productService;
			
	/**
	 * Creates a new product in the database
	 * @param product a product model. id and account id will be ignored.
	 * @param request httpservletrequest, used for getting session information.
	 */
	@RequestMapping(path="/create")
	public void createProduct(@RequestBody Product product, HttpServletRequest request) {
		this.productService.createProduct(product, request);
	}	
	
	/**
	 * Search for a product with a matching id
	 * @param id a request parameter "id" with an integer value
	 * @return a product object.
	 */
	@RequestMapping(path="/search/id")
	public Product getByID(@RequestParam int id) {
		return this.productService.getProductById(id);
	}

	/**
	 * Search's all products with a name that starts with the provided string.
	 * The search is case insensitive so Asdf will can return ASDF1 and asdf2
	 * @param productname a request parameter "productname" with the value being the 
	 * name of the product being searched.
	 * @return All products whose name start with productname
	 */
	@RequestMapping(path="/search/productname")
	public List<Product> getByProductname(@RequestParam String productname) {
		return this.productService.getProductByName(productname);
	}

	
	/**
	 * Will provide all products with its tag's strings in the list tagnames. 
	 * The products must have all the tags in tagnames in in order to be returned.
	 * Products with banned tags will not be included in the returned products.
	 * Is case sensitive because the In keyword used in query methods is 
	 * incompatible with the IgnoreCase keyword.
	 * @param tagnames a list of strings such as asdf1,asdf2
	 * @return a list of products with no duplicates with all requested tags
	 */
	@RequestMapping(path="/search/tagnames")
	public List<Product> getByTagName(@RequestParam List<String> tagnames) {
		return this.productService.getProductByTagsName(tagnames);
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
	 * Updates a product. Will overwrite everything except id and accountid.
	 * This includes productotag relationships. If nothing or null is passed,
	 * then the data in the database will not be overwritten.
	 * @param product the product being overwritten with its new attributes. Minimum
	 * requirement is the id so the product can be identified in the database.
	 * @param request used to find the session account.
	 */
	@RequestMapping(path="/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateProduct(@RequestBody Product product, HttpServletRequest request) {
		this.productService.updateProduct(product, request);
	}	
		
	@RequestMapping(path="/{id}/buy")
	public String buyProduct(@PathVariable int id, HttpServletRequest request) {
		return this.productService.buyProduct(id, request);
	}

	/**
	 * Deletes a product from the database. Will also handle removing its producttotag relationships.
	 * @param product A product object. JPA only requires an id for JSON.
	 */
	@RequestMapping(path="/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteProduct(@RequestBody Product product) {
		this.productService.deleteProduct(product);
	}		
	
}


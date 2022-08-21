package com.jaty.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaty.models.Account;
import com.jaty.models.Product;
import com.jaty.models.ProductToTag;
import com.jaty.models.ProductToTags;
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
	
	@Autowired
	Logger log;
		
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
	 * Search's all products with a name start starts with the provided string
	 * @param productname a request parameter "productname with the value being the 
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
	 * @param tagnames a list of strings such as [asdf1,asdf2] 
	 * @return a list of products with no duplicates with all requested tags
	 */
	@RequestMapping(path="/search/tagnames")
	public List<Product> getByTagName(@RequestBody List<String> tagnames) {
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
	 * saves the product to the database.
	 * AccountId is currently manually included with the inputed json
	 * will need to leech off the account's httpsession for the accountid
	 * Currently is incapable of being passed with its tags. 
	 * @param product A Product Object.
	 */
	@RequestMapping(path="/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody Product product) {
		this.productService.saveProduct(product);
	}

	/**
	 * Adds 1 tag to a product. Has largely become irrelevant due to saveTags().
	 * However, the ProductToTag module is a fully functional model 
	 * unlike ProductToTags which doesn't map to any table
	 * so I'm going to leave this one in for now.
	 * @param pt check the producttotag module for the request body json. 
	 * The product and tag must contain an id that matches those in the table
	 */
	@RequestMapping(path="/save/tag", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveTag(@RequestBody ProductToTag pt) {
		this.productService.saveTag(pt.getProduct(),pt.getTag());
	}		
	
	/**
	 * Adds a list of tags to a product
	 * @param pt See the ProductToTags module for json representation.
	 * It only checks ids for the product and tags.
	 */
	@RequestMapping(path="/save/tags", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveTags(@RequestBody ProductToTags pt) {
		this.productService.saveTags(pt.getProduct(),pt.getTags());
	}		
}


package com.jaty.controller;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000", allowCredentials="true")
public class ProductController {

	@Autowired
	ProductService productService;
			
	/**
	 * Creates a new product in the database
	 * @param product a product model. id and account id will be ignored.
	 * @param request httpservletrequest, used for getting session information.
	 * @return true if the product is created in the database
	 */
	@CrossOrigin
	@RequestMapping(path="/create")
	public String createProduct(@RequestBody Product product, HttpServletRequest request) {
		return this.productService.createProduct(product, request);
	}	
	
	/**
	 * Search for a product with a matching id
	 * @param id a request parameter "id" with an integer value
	 * @return a product object.
	 */
	@CrossOrigin
	@RequestMapping(path="/search/id")
	public Product getByID(@RequestParam int id) {
		return this.productService.getProductById(id);
	}

	/**
	 * Search's all products with a name that starts with the provided string.
	 * The search is case insensitive so Asdf will can return ASDF1 and asdf2
	 * @param productname a request parameter "productname" with the value being the 
	 * name of the product being searched.
	 * @return All products whose name start with productname. Searching by
	 * and empty string will return all products. 
	 * /product/search/productname?productname=
	 */
	@CrossOrigin
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
	@CrossOrigin
	@RequestMapping(path="/search/tagnames")
	public List<Product> getByTagName(@RequestParam List<String> tagnames) {
		return this.productService.getProductByTagsName(tagnames);
	}
	
	/**
	 * Search for products owned by the account logged in
	 * @return a list of products. Will return an empty list if 
	 * not logged in.
	 */
	@CrossOrigin
	@RequestMapping(path="/search/myaccount")
	public List<Product> getByMyAccount(HttpServletRequest request) {
		return this.productService.getMyProduct(request);
	}

	/**
	 * Will provide all products listed under the provided account
	 * @param account the account whose products we are viewing
	 * @return a list of products who belong to the inputted account
	 */
	@CrossOrigin
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
	 * @return a string describing how the update failed or if it succeeded.
	 */
	@CrossOrigin
	@RequestMapping(path="/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateProduct(@RequestBody Product product, HttpServletRequest request) {
		return this.productService.updateProduct(product, request);
	}	
	
	/**
	 * Uploads a png file to the bucket. The file should be sent as a binary.
	 * @param id The id of the product the picture is attached to
	 * @param in an inputstream object. 
	 * According to postman, this is sent as a binary.
	 * The binary is not recognized as part of the RequestBody but it does
	 * occupy the body tab in postman.
	 * @param request used to check session information
	 * @return string describing success or failure
	 */
	@CrossOrigin
	@RequestMapping(path="/update/picture")
	public String updatePicture(@RequestParam int id, InputStream in, HttpServletRequest request) {
		return this.productService.updatePicture(id,in,request);
	}	
	
	
	/**
	 * Retrieves product id from path URL and account id from the HttpSession which will be used to
	 * retrieve the buyer wallet. Checks to see if the buyer can afford the product; if they can 
	 * afford it the price is removed from their balance, added to the product owner's balance and
	 * the product's stock is reduced by 1.
	 * @param id of product to be purchased
	 * @param request contains the account id of the buyer
	 * @return modifications to the buyer wallet, seller wallet and purchased product in the database.
	 */
	@CrossOrigin
	@RequestMapping(path="/{id}/buy")
	public String buyProduct(@PathVariable int id, HttpServletRequest request) {
		return this.productService.buyProduct(id, request);
	}

	/**
	 * Deletes a product from the database. Will also handle removing its producttotag relationships.
	 * @param product A product object. JPA only requires an id for JSON.
	 * @return true if the product was deleted from the database.
	 */
	@CrossOrigin
	@RequestMapping(path="/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String deleteProduct(@RequestBody Product product, HttpServletRequest request) {
		return this.productService.deleteProduct(product, request);
	}		

	/**
	 * Retrieves product id from path URL, adjustment amount is retrieved from a request paramater and
	 * permissions are retrieved from the HttpSession. This particular end-point edits the stock of a
	 * product.
	 * @param id of product to be edited
	 * @param adjustment 
	 * @param request contains the account id need to determine permissions
	 * @return modifications to the selected product in the database. 
	 */
	@CrossOrigin
	@RequestMapping(path="/{id}/adjuststock")
	public String adjustStock(@PathVariable int id, @RequestParam int adjustment, HttpServletRequest request) {
		return this.productService.adjustProductStock(id, adjustment, request);
	}
	
	/**
	 * Retrieves product id from path URL, adjustment amount is retrieved from a request paramater and
	 * permissions are retrieved from the HttpSession. This particular end-point edits the price of a
	 * product.
	 * @param id of product to be edited
	 * @param adjustment 
	 * @param request contains the account id need to determine permissions
	 * @return modifications to the selected product in the database. 
	 */
	@CrossOrigin
	@RequestMapping(path="/{id}/adjustprice")
	public String adjustPrice(@PathVariable int id, @RequestParam int adjustment, HttpServletRequest request) {
		return this.productService.overwriteProductPrice(id, adjustment, request);
	}
	
	/**
	 * Retrieves product id from path URL, adjustment amount is retrieved from a request paramater and
	 * permissions are retrieved from the HttpSession. This particular end-point edits the description
	 * of a product.
	 * @param id of product to be edited
	 * @param edit 
	 * @param request contains the account id need to determine permissions
	 * @return modifications to the selected product in the database. 
	 */
	@CrossOrigin
	@RequestMapping(path="/{id}/adjustdescription")
	public String adjustDescription(@PathVariable int id, @RequestParam String edit, HttpServletRequest request) {
		return this.productService.overwriteProductDescription(id, edit, request);
	}
}


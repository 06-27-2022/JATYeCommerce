package com.jaty.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaty.models.Account;
import com.jaty.models.Product;
import com.jaty.models.Tag;
import com.jaty.models.Wallet;
import com.jaty.repository.AccountRepository;
import com.jaty.repository.ProductRepository;
import com.jaty.repository.TagRepository;
import com.jaty.repository.WalletRepository;
import com.jaty.utils.BucketUtil;

@Service("jatyProductService")
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private BucketUtil bucketUtil;
	
	public ProductService() {}

	/**
	 * gets the account currently logged in
	 * @param request
	 * @return
	 */
	private Account getSessionAccount(int accountid) {
		try{
			return this.accountRepository.findById(accountid);		
		}catch(NullPointerException e) {
			return null;
		}
	}
	/**
	 * checks if the account id matches the product's accountid.
	 * Otherwise, checks if the account's role is moderator.
	 * @param a account object with an id. Should be obtained from session data
	 * @param p product object with an id. Should be a product in the database.
	 * @return true if you have permission to access the product
	 */
	private boolean permission(Account a, Product p) {
		if(a==null||p==null)return false;
		if(p.getAccountId().getId()!=a.getId()&&!a.getRole().equalsIgnoreCase(Account.MODERATOR))
			return false;
		return true;
	}
	
	/**
	 * checks if the product contains any banned tags
	 * @param product
	 * @return true if the product has any banned tags
	 */
	private boolean isBanned(Product product) {
		Tag[]tags=product.getTags().toArray(new Tag[] {});
		Arrays.sort(tags,(t1,t2)->{
			int a=t1.getBan()?0:1;
			int b=t2.getBan()?0:1;
			return a-b;
		});
		return tags[0].getBan();
	}

	/**
	 * Checks if product p contains all tags using the provided tagnames
	 * @param p a product object
	 * @param tagnames list of tags names used by tags
	 * @return true if the product contains a matching tag for every provided tagname
	 */
	private boolean containsTagnames(Product p,List<String>tagnames) {
		int count=0;
		for(Tag t:p.getTags())
			if(tagnames.contains(t.getTag())){
				count++;
				if(count==tagnames.size()) {
					return true;
				}
			}
		return false;
	}

	private void saveProduct(Product product) {
		this.productRepository.save(product);
	}

	public String createProduct(Product product,int accountid) {
		//Check if client is logged in
		Account a=getSessionAccount(accountid);
		if(a == null||product==null) return "not-logged-in";

		product.setId(0);
		product.setAccountId(a);
		//confirm the tags provided exist
		if(product.getTags()!=null) {
			Set<Tag>tags=new HashSet<Tag>();
			for(Tag tag:product.getTags()) {
				//confirm tag exists
				Tag t=this.tagRepository.findById(tag.getId());
				if(t==null)continue;
				//add tag to product
				tags.add(t);
			}
			product.setTags(tags);
		}
		this.productRepository.save(product);
		return "success";
	}
	
	public Product getProductById(int id) {
		return this.productRepository.findById(id);
	}
	
	public List<Product> getMyProduct(int accountid) {
		Account acc = getSessionAccount(accountid);
		if(acc==null)return new ArrayList<Product>();
		return this.productRepository.findByAccountid(acc);
	}

	
	
	public List<Product> getProductByName(String productname){
		return this.productRepository.findByNameStartingWithIgnoreCase(productname);
	}
	
	public List<Product> getProductByTagsName(List<String> tagnames) {
		List<Product>allproducts=this.productRepository.findDistinctByTagsTagIn(tagnames);
		List<Product>products=new LinkedList<Product>();
		for(Product p:allproducts) {
			//if the product is banned, we skip to the next product
			if(isBanned(p)) {continue;}

			//check if the product has all the requested tags
			if(containsTagnames(p, tagnames))
				products.add(p);
		}
		return products;
	}
	
	
	public List<Product> getProductByAccount(Account account) {
		return this.productRepository.findByAccountid(account);
	}

	public String updateProduct(Product product, int accountid) {
		//confirm login 
		Account a=getSessionAccount(accountid);
		if(a==null)return "not-logged-in";		
		//confirm product exists
		Product p=this.productRepository.findById(product.getId());
		if(p==null)return "product-does-not-exist";
		//check if you own the product or you're a moderator
		if(!permission(a,p))return "do-not-have-permission";
		//overwriting product's attributes, excluding its id and accountid
		if(product.getDescription()!=null)p.setDescription(product.getDescription());
		if(product.getName()!=null)p.setName(product.getName());
		if(product.getPicture()!=null)p.setPicture(product.getPicture());
		if(product.getPrice()>=0)p.setPrice(product.getPrice());
		if(product.getStock()>=0)p.setStock(product.getStock());
		if(product.getTags()!=null) {
			Set<Tag>tags=new HashSet<Tag>();
			for(Tag tag:product.getTags()) {
				//confirm tag exists
				Tag t=this.tagRepository.findById(tag.getId());
				if(t==null)continue;
				//add tag to product
				tags.add(t);
			}
			p.setTags(tags);
		}
		//save changes
		saveProduct(p);					
		return "success";
	}
	
	public String updatePicture(int id,InputStream in, int accountid) {
		//confirm login 
		Account a=getSessionAccount(accountid);
		if(a==null)return "not-logged-in";		
		//confirm product exists
		Product p=this.productRepository.findById(id);
		if(p==null)return "product-does-not-exist";
		//check if you own the product or you're a moderator
		if(!permission(a,p))return "do-not-have-permission";		

		String key="key"+id+".png";
		
		bucketUtil.uploadInputStream(in, key);
		p.setPicture(key);
		updateProduct(p, accountid);
		return "success";
	}
		
	public String deleteProduct(Product product, int accountid) {
		Account acc=getSessionAccount(accountid);
		if(acc==null)return "not-logged-in";
		Product p=getProductById(product.getId());
		if(!permission(acc,p))return "do-not-have-permission";
		this.productRepository.delete(product);
		return "success";
	}
	
	public String buyProduct(int id, int accountid) {
		//Check if client is logged in for buying
//		HttpSession session = request.getSession(false);
//		if(session != null) {
		if(accountid >= 0) {
			//Retrieve product for purchase, account of buyer, and the buyer's wallet
			Product purchase = getProductById(id);
			Wallet buyerWallet = this.walletRepository.findByAccountId(this.accountRepository.findById(accountid));
			if(buyerWallet.getBalance()<purchase.getPrice() && purchase.getStock() < 0) {
				//If buyer balance or purchase is out of stock then no further logic is done
				//This can be broken down to give more details to client
				return "cannot-afford-product-or-out-of-stock";
			}else {
				//Update buyer balance, purchase stock and seller balance after successful purchase
				buyerWallet.setBalance(buyerWallet.getBalance()-purchase.getPrice());
				purchase.setStock(purchase.getStock()-1);
				walletRepository.save(buyerWallet);
				saveProduct(purchase);
				Wallet sellerWallet = walletRepository.findByAccountId(purchase.getAccountId());
				sellerWallet.setBalance(sellerWallet.getBalance()+purchase.getPrice());
				walletRepository.save(sellerWallet);
				return "product-bought";
			}
			
		}
		//If client is not logged in they are informed
		return "not-logged-in";
	}
}

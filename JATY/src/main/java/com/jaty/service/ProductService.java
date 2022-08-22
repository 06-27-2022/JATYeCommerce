package com.jaty.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	public ProductService() {}
	
	public Product getProductById(int id) {
		return this.productRepository.findById(id);
	}
	
	public List<Product> getProductByName(String productname){
		return this.productRepository.findByNameStartingWithIgnoreCase(productname);
	}
	
	public List<Product> getProductByTagsName(List<String> tagnames) {
		List<Product>allproducts=this.productRepository.findDistinctByTagsTagIn(tagnames);
		List<Product>products=new LinkedList<Product>();
		for(Product p:allproducts) {
			//if the product is banned, we skip to the next product
			if(isBan(p)) {continue;}

			//check if the product has all the requested tags
			if(containsTagnames(p, tagnames))
				products.add(p);
		}
		return products;
	}
	
	
	/**
	 * checks if the product contains any banned tags
	 * @param product
	 * @return true if the product has any banned tags
	 */
	private boolean isBan(Product product) {
		Tag[]tags=product.getTags().toArray(new Tag[] {});
		Arrays.sort(tags,(t1,t2)->{
			int a=t1.isBan()?0:1;
			int b=t2.isBan()?0:1;
			return a-b;
		});
		return tags[0].isBan();
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
	
	public List<Product> getProductByAccount(Account account) {
		return this.productRepository.findByAccountid(account);
	}

	
	public void saveProduct(Product product) {
		this.productRepository.save(product);
	}
	
	public void saveTags(Product product, List<Tag>tags) {		
		//confirm product exists
		Product p=this.productRepository.findById(product.getId());
		if(p==null)return;

		System.out.println("before");
		p.getTags().forEach((t)->{System.out.println("productid"+p.getId()+" tagid"+t.getId());});

		//adding in the individual tags one by one
		for(Tag tag:tags) {
			//confirm tag exists
			Tag t=this.tagRepository.findById(tag.getId());
			if(t==null)continue;
			//add tag to product
			p.addTag(t);
		}

		System.out.println("after");
		p.getTags().forEach((t)->{System.out.println("productid"+p.getId()+" tagid"+t.getId());});
		//save changes
		saveProduct(p);					
	}
	
	public void deleteProduct(Product product) {
		this.productRepository.delete(product);
	}
	
	public void deleteTags(Product product, List<Tag>tags) {
		//confirm product exists
		Product p=this.productRepository.findById(product.getId());
		if(p==null)return;
		//adding in the individual tags one by one
		for(Tag tag:tags) {
			//confirm tag is not null
			if(tag==null)continue;
			//remove tag's relationship with product
			p.removeTag(tag.getId());
		}
		//save changes
		saveProduct(p);					
	}
	
	public String buyProduct(int id, HttpServletRequest request) {
		//Check if client is logged in for buying
		HttpSession session = request.getSession(false);
		if(session != null) {
			//Retrieve product for purchase, account of buyer, and the buyer's wallet
			Product purchase = getProductById(id);
			Wallet buyerWallet = this.walletRepository.findByAccountId(this.accountRepository.findById((int) session.getAttribute("accountId")));
			if(buyerWallet.getBalance()<purchase.getPrice() && purchase.getStock() > 0) {
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

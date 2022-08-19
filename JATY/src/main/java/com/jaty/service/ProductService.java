package com.jaty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaty.models.Account;
import com.jaty.models.Product;
import com.jaty.models.Tag;
import com.jaty.repository.ProductRepository;

@Service("jatyProductService")
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public ProductService() {}
	
	public Product getProductById(int id) {
		return this.productRepository.findById(id);
	}
	public List<Product> getProductByTags(Tag tags) {
		return this.productRepository.findByTags(tags);
	}
	public List<Product> getProductByAccount(Account account) {
		return this.productRepository.findByAccountid(account);
	}
	public void saveProduct(Product product) {
		this.productRepository.save(product);
	}
}

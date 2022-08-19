package com.jaty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaty.models.Product;
import com.jaty.repository.ProductRepository;

@Service("jatyProductService")
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public ProductService() {}
	
	public Product getProduct(int id) {
		return this.productRepository.findById(id);
	}
	
	public void saveProduct(Product product) {
		this.productRepository.save(product);
	}
}

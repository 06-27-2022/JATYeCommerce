package com.jaty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaty.models.Product;
import com.jaty.models.Tag;
import com.jaty.repository.ProductRepository;
import com.jaty.repository.TagRepository;

@Service("jatyTagService")
public class TagService {

	@Autowired
	private TagRepository tagRepository;

	@Autowired 
	private ProductRepository productRepository;
	
	public TagService() {}
	
	public Tag getTag(int id) {
		return this.tagRepository.findById(id);
	}
	public Tag getTag(String tagname) {
		return this.tagRepository.findByTag(tagname);
	}
	public List<Tag>searchTag(String tagname){
		return this.tagRepository.findByTagStartingWithIgnoreCase(tagname);
	}
	
	public void save(Tag tag) {
		this.tagRepository.save(tag);
	}
	
	public void delete(Tag tag) {
		//confirm tag exists
		Tag t=getTag(tag.getId());
		if(t==null)return;
		//remove relationship with all products
		List<Product>products=this.productRepository.findDistinctByTags(tag);
		for(Product p:products) {
			if(p.getTags().contains(t)) {
				p.removeTag(tag.getId());
				this.productRepository.save(p);
			}			
		}
		this.tagRepository.delete(tag);
	}
	
}

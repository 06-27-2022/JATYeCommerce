package com.jaty.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaty.models.Account;
import com.jaty.models.Product;
import com.jaty.models.Tag;
import com.jaty.repository.AccountRepository;
import com.jaty.repository.ProductRepository;
import com.jaty.repository.TagRepository;

@Service("jatyTagService")
public class TagService {

	@Autowired
	private TagRepository tagRepository;

	@Autowired 
	private ProductRepository productRepository;

	@Autowired
	private AccountRepository accountRepository;

	public TagService() {}
	
	private boolean permission(HttpServletRequest request) {
		Account acc=this.accountRepository.findById((int)request.getSession(false).getAttribute("accountId"));		
		if(acc.getRole().equals(Account.DEFAULT))return false;
		return true;
	}
		
	public boolean create(Tag tag, HttpServletRequest request) {
		if(!permission(request))return false;
		//check if tag exists
		if(getTag(tag.getTag())!=null)return false;
		//create new tag
		this.tagRepository.save(tag);
		return true;
	}

	public Tag getTag(int id) {
		return this.tagRepository.findById(id);
	}
	public Tag getTag(String tagname) {
		return this.tagRepository.findByTag(tagname);
	}
	public List<Tag> getAll(){
		return this.tagRepository.findAll();
	}
	public List<Tag>searchTag(String tagname){
		return this.tagRepository.findByTagStartingWithIgnoreCase(tagname);
	}
	
	public boolean update(Tag tag, HttpServletRequest request) {
		if(!permission(request))return false;
		//check if tag exists
		Tag t=getTag(tag.getId());
		if(t==null)return false;
		//save changes
		t.setBan(tag.getBan());
		this.tagRepository.save(t);
		return true;
	}
	
	public boolean delete(Tag tag, HttpServletRequest request) {
		if(!permission(request))return false;
		//confirm tag exists
		Tag t=getTag(tag.getId());
		if(t==null)return false;
		//remove relationship with all products
		List<Product>products=this.productRepository.findDistinctByTags(tag);
		for(Product p:products) {
			if(p.getTags().contains(t)) {
				p.removeTag(tag.getId());
				this.productRepository.save(p);
			}			
		}
		this.tagRepository.delete(tag);
		return true;
	}
	
}

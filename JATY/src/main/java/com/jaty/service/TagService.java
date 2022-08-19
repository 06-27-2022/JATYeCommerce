package com.jaty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaty.models.Tag;
import com.jaty.repository.TagRepository;

@Service("jatyTagService")
public class TagService {

	@Autowired
	private TagRepository tagRepository;

	public TagService() {}
	
	public Tag getTag(int id) {
		return this.tagRepository.findById(id);
	}
	public Tag getTag(String tagname) {
		return this.tagRepository.findByTag(tagname);
	}
	
	public void save(Tag tag) {
		this.tagRepository.save(tag);
	}
	
}

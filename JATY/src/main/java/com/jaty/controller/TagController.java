package com.jaty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaty.models.Tag;
import com.jaty.service.TagService;

@RestController("jatyTagController")
@RequestMapping(path="/tag")
public class TagController {
	@Autowired
	TagService tagService;
		
	@RequestMapping(path="/get")
	public Tag get(@RequestParam int id) {
		return this.tagService.getTag(id);
	}
	
	@RequestMapping(path="/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Tag tag) {
		this.tagService.save(tag);
	}

}

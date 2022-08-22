package com.jaty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaty.models.Tag;
import com.jaty.service.TagService;

/**
 * check the modules for json representations of the objects
 * @author tomh0
 *
 */
@RestController("jatyTagController")
@RequestMapping(path="/tag")
public class TagController {
	@Autowired
	TagService tagService;
		
	/**
	 * finds a tag with a matching id
	 * @param id a request parameter "id" with an integer value
	 * @return a tag object from the database
	 */
	@RequestMapping(path="/get")
	public Tag get(@RequestParam int id) {
		return this.tagService.getTag(id);
	}
		
	/**
	 * Searches all tags for tagnames that start with the String tagname.
	 * For example, searching asdf1 would return tags with asdf1 asdf1234 while excluding tags with 
	 * asdf2 and asdf3.
	 * Ignores case so ASDF1 can return asdf1 and asdf1234
	 * @param tagname a request parameter called "tagname" which indicates what the desired tag's tagname starts with
	 * @return a list of tags from the database.
	 */
	@RequestMapping(path="/search")
	public List<Tag> search(@RequestParam String tagname) {
		return this.tagService.searchTag(tagname);
	}
	
	/**
	 * Saves a tag onto the database. Only effects the tags table and can create or update tags.
	 * Will likely implement more controlled implementations in the future.
	 * @param tag A request body consisting of an entire Tag object.
	 */
	@RequestMapping(path="/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Tag tag) {
		this.tagService.save(tag);
	}
	
	/**
	 * https://stackoverflow.com/questions/14585836/hibernate-many-to-many-cascading-delete?rq=1
	 * deletes a single tag from the database. Will handle all relationships in
	 * the producttotag join table
	 * @param tag a single tag object. Requires an id, all other tag information is not used.
	 */
	@RequestMapping(path="/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@RequestBody Tag tag) {
		this.tagService.delete(tag);
	}
	

}

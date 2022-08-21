package com.jaty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaty.models.Tag;

@Repository("jatyTagRepository")
public interface TagRepository extends JpaRepository<Tag, Integer>{
	<S extends Tag> S save(S entity);

	Tag findById(int id);
	
	Tag findByTag(String tagname);
	
	List<Tag>findByTagStartingWithIgnoreCase(String tagname);
	
	void delete(Tag tag);
	
}

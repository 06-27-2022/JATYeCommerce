package com.jaty.models.utils;

import java.util.List;

import com.jaty.models.Product;
import com.jaty.models.Tag;

/**
 * 	{
		"product":{"id": 11},
		"tags":[{"id": 7},{"id": 5},{"id": 9}]
	}
 * <br>
 * utility class for housing a product and multiple tags in a single requestbody
 * @author tomh0
 *
 */
public class ProductToTags{
	private Product product;
	private List<Tag>tags;
	public ProductToTags() {}
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**
	 * @return the tags
	 */
	public List<Tag> getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
}
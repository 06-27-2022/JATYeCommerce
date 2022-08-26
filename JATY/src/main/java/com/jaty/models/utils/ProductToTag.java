package com.jaty.models.utils;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.jaty.models.Product;
import com.jaty.models.Tag;

/**
 * {
"product":{"id": 4},
"tag":{"id": 1}
}
 * @author tomh0
 *
 */
@Entity
@Table(name="jatyproducttotag")
public class ProductToTag{

	@EmbeddedId
	ProductToTagKey id;
	
	@ManyToOne
	@MapsId("productid")
	@JoinColumn(name="productid")
	Product product;
	
	@ManyToOne
	@MapsId("tagid")
	@JoinColumn(name="tagid")
	Tag tag;

	/**
	 * @return the id
	 */
	public ProductToTagKey getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ProductToTagKey id) {
		this.id = id;
	}

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
	 * @return the tag
	 */
	public Tag getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(Tag tag) {
		this.tag = tag;
	}	
}

@Embeddable
class ProductToTagKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7222739367997409340L;
	@Column(name="productid")
	private int productid;
	@Column(name="tagid")
	private int tagid;
	/**
	 * @return the productid
	 */
	public int getProductid() {
		return productid;
	}
	/**
	 * @param productid the productid to set
	 */
	public void setProductid(int productid) {
		this.productid = productid;
	}
	/**
	 * @return the tagid
	 */
	public int getTagid() {
		return tagid;
	}
	/**
	 * @param tagid the tagid to set
	 */
	public void setTagid(int tagid) {
		this.tagid = tagid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productid;
		result = prime * result + tagid;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductToTagKey other = (ProductToTagKey) obj;
		if (productid != other.productid)
			return false;
		if (tagid != other.tagid)
			return false;
		return true;
	}
}

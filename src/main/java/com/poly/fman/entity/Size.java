package com.poly.fman.entity;



import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the size database table.
 * 
 */
@Entity
@Table(name="size")
@NamedQuery(name="Size.findAll", query="SELECT s FROM Size s")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Size implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte active;

	private Float length;

	private Float size;

	private Float width;

	//bi-directional many-to-one association to ProductSize
	
	@OneToMany(mappedBy="size")
	private List<ProductSize> productSizes;


	public List<ProductSize> getProductSizes() {
		return this.productSizes;
	}

	public void setProductSizes(List<ProductSize> productSizes) {
		this.productSizes = productSizes;
	}

	public ProductSize addProductSize(ProductSize productSize) {
		getProductSizes().add(productSize);
		productSize.setSize(this);

		return productSize;
	}

	public ProductSize removeProductSize(ProductSize productSize) {
		getProductSizes().remove(productSize);
		productSize.setSize(null);

		return productSize;
	}

}
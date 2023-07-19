package com.poly.fman.dto;

import java.math.BigInteger;
import java.util.Date;


import com.poly.fman.entity.Brand;
import com.poly.fman.entity.ProductType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO2 implements ModelDTO{
    
	@NotNull(message = "{NotNull.product.id}")
	@NotBlank(message = "{NotBlank.product.id}")
	private String id;

	private byte active;

	private Date createAt;

	private Date deleteAt;
	
	@NotNull(message = "{NotNull.product.desc}")
	@NotBlank(message = "{NotBlank.product.desc}")
	private String desc;
	
	@NotNull(message = "{NotNull.product.name}")
	@NotBlank(message = "{NotBlank.product.name}")
	private String name;

	private String image;
	
	@Min(value = 200000, message = "{Min.product.price}")
	@Max(value = 10000000, message = "{Max.product.price}")
	@NotNull(message = "{NotNull.product.price}")
	private BigInteger price;

	private Date updateAt;

    private ProductType productType;

	private Brand brand;
}

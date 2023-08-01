package com.poly.fman.dto.model;

import java.util.Date;

import com.poly.fman.entity.Product;
import com.poly.fman.entity.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSizeDTO2 implements ModelDTO {

	private int id;

	private byte active;

	private Date createAt;

	private Date deleteAt;

	private int quantity;

	private Date updateAt;
	
	private Product product;
	
	private Size size;
}

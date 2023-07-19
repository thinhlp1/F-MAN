package com.poly.fman.dto;

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
public class ProductSizeDTO implements ModelDTO, ResponseDTO {

	private int id;

	private byte active;

	private Date createAt;

	private Date deleteAt;

	private int quantity;

	private Date updateAt;
	
	private ProductDTO product;
	
	private SizeDTO size;
}

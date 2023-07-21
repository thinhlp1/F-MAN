package com.poly.fman.dto.model;

import java.math.BigInteger;
import java.util.Date;

import com.poly.fman.entity.Cart;
import com.poly.fman.entity.Product;
import com.poly.fman.entity.ProductSize;
import com.poly.fman.service.common.CommonUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO2 implements ModelDTO  {
    
	private int id;

	private Date createAt;

	private int quantity;

	private Date updateAt;

	private Integer cartId;

    private String productId;

	private String productSizeId;

	private Cart cart;

	private Product product;

	private ProductSize productSize;

	public String getSubTotalStringVND(){
		long subTotal = 0;
		subTotal = product.getPrice().intValue() * quantity;
		return CommonUtils.convertToCurrencyString(BigInteger.valueOf(subTotal), " VNĐ");
	}
}

package com.poly.fman.dto;

import com.poly.fman.entity.Order;
import com.poly.fman.entity.Product;
import com.poly.fman.entity.ProductSize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO implements ModelDTO {

	private int id;

	private int quantity;

    private String orderId;

    private String productId;

    private String productSizeId;

	private Order order;

	private Product product;

	private ProductSize productSize;
}

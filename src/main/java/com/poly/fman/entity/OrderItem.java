package com.poly.fman.entity;



import java.io.Serializable;
import java.math.BigInteger;

import com.poly.fman.service.common.CommonUtils;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the order_items database table.
 * 
 */
@Entity
@Table(name="order_items")
@NamedQuery(name="OrderItem.findAll", query="SELECT o FROM OrderItem o")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int quantity;

	private BigInteger productPrice; 

	//bi-directional many-to-one association to Order
	@ManyToOne
	private Order order;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

	//bi-directional many-to-one association to ProductSize
	@ManyToOne
	@JoinColumn(name="product_size_id")
	private ProductSize productSize;

	public String getTotalStringVND(){
		return CommonUtils.convertToCurrencyString(BigInteger.valueOf(product.getPrice().intValue() * quantity), " VNĐ");
	}

}
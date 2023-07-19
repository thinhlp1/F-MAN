package com.poly.fman.entity;



import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * The persistent class for the cart_items database table.
 * 
 */
@Entity
@Table(name="cart_items")
@NamedQuery(name="CartItem.findAll", query="SELECT c FROM CartItem c")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_at")
	private Date createAt;

	private int quantity;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_at")
	private Date updateAt;

	//bi-directional many-to-one association to Cart
	@ManyToOne
	@JoinColumn(name="card_id")
	private Cart cart;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	//bi-directional many-to-one association to ProductSize
	@ManyToOne
	@JoinColumn(name="product_size_id")
	private ProductSize productSize;

}
package com.poly.fman.entity;



import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poly.fman.service.common.CommonUtils;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name="product")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte active;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_at")
	private Date createAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="delete_at")
	private Date deleteAt;

	
	private String desc;

	private String name;

	private String image;

	private BigInteger price;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_at")
	private Date updateAt;

	//bi-directional many-to-one association to CartItem
	@JsonIgnore
	@OneToMany(mappedBy="product")
	private List<CartItem> cartItems;

	//bi-directional many-to-one association to OrderItem
	@JsonIgnore
	@OneToMany(mappedBy="product")
	private List<OrderItem> orderItems;

	//bi-directional many-to-one association to Brand
	
	@JsonIgnore
	@ManyToOne
	private Brand brand;

	//bi-directional many-to-one association to ProductType
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="product_type")
	private ProductType productType;

	//bi-directional many-to-one association to ProductHistory
	@JsonIgnore
	@OneToMany(mappedBy="product")
	private List<ProductHistory> productHistories;

	//bi-directional many-to-one association to ProductSize
	@JsonIgnore
	@OneToMany(mappedBy="product")
	private List<ProductSize> productSizes;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name="update_by")
	private User updateBy;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name="create_by")
	private User createBy;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name="delete_by")
	private User deleteBy;




	public List<CartItem> getCartItems() {
		return this.cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public CartItem addCartItem(CartItem cartItem) {
		getCartItems().add(cartItem);
		cartItem.setProduct(this);

		return cartItem;
	}

	public CartItem removeCartItem(CartItem cartItem) {
		getCartItems().remove(cartItem);
		cartItem.setProduct(null);

		return cartItem;
	}

	public List<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItem addOrderItem(OrderItem orderItem) {
		getOrderItems().add(orderItem);
		orderItem.setProduct(this);

		return orderItem;
	}

	public OrderItem removeOrderItem(OrderItem orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setProduct(null);

		return orderItem;
	}

	public List<ProductHistory> getProductHistories() {
		return this.productHistories;
	}

	public void setProductHistories(List<ProductHistory> productHistories) {
		this.productHistories = productHistories;
	}

	public ProductHistory addProductHistory(ProductHistory productHistory) {
		getProductHistories().add(productHistory);
		productHistory.setProduct(this);

		return productHistory;
	}

	public ProductHistory removeProductHistory(ProductHistory productHistory) {
		getProductHistories().remove(productHistory);
		productHistory.setProduct(null);

		return productHistory;
	}

	public List<ProductSize> getProductSizes() {
		return this.productSizes;
	}

	public void setProductSizes(List<ProductSize> productSizes) {
		this.productSizes = productSizes;
	}

	public ProductSize addProductSize(ProductSize productSize) {
		getProductSizes().add(productSize);
		productSize.setProduct(this);

		return productSize;
	}

	public ProductSize removeProductSize(ProductSize productSize) {
		getProductSizes().remove(productSize);
		productSize.setProduct(null);

		return productSize;
	}

	public String getPriceStringVND(){
		return CommonUtils.convertToCurrencyString(price, " VNĐ");
	}

}
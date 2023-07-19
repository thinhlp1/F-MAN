package com.poly.fman.entity;



import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the product_size database table.
 * 
 */
@Entity
@Table(name="product_size")
@NamedQuery(name="ProductSize.findAll", query="SELECT p FROM ProductSize p")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSize implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private byte active;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_at")
	private Date createAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="delete_at")
	private Date deleteAt;

	private int quantity;

	@Transient
	private int availableQuantity;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_at")
	private Date updateAt;

	//bi-directional many-to-one association to CartItem
	@OneToMany(mappedBy="productSize")
	private List<CartItem> cartItems;

	//bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy="productSize")
	private List<OrderItem> orderItems;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

	//bi-directional many-to-one association to Size
	@ManyToOne
	private Size size;

	
	public List<CartItem> getCartItems() {
		return this.cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public CartItem addCartItem(CartItem cartItem) {
		getCartItems().add(cartItem);
		cartItem.setProductSize(this);

		return cartItem;
	}

	public CartItem removeCartItem(CartItem cartItem) {
		getCartItems().remove(cartItem);
		cartItem.setProductSize(null);

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
		orderItem.setProductSize(this);

		return orderItem;
	}

	public OrderItem removeOrderItem(OrderItem orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setProductSize(null);

		return orderItem;
	}

	
    public int getAvailableQuantity() {
		System.out.println("QUANTITY: " + quantity);
			System.out.println("AVAILABLE QUAN : "  + quantity);
        return quantity - getOrderedQuantityWithState("PENDING_APPROVAL");
    }

    private int getOrderedQuantityWithState(String orderStateId) {
        int orderedQuantity = 0;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getOrder().getOrderState().getId().equals(orderStateId)) {
                orderedQuantity += orderItem.getQuantity();
            }
        }
        return orderedQuantity;
    }


}
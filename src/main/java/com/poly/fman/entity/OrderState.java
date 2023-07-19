package com.poly.fman.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the order_state database table.
 * 
 */
@Entity
@Table(name = "order_state")
@NamedQuery(name = "OrderState.findAll", query = "SELECT o FROM OrderState o")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	// bi-directional many-to-one association to Order
	@OneToMany(mappedBy = "orderState")
	private List<Order> orders;

}
package com.poly.fman.entity;

import java.io.Serializable;
import java.util.List;

import com.poly.fman.repository.AddressRepository;
import com.poly.fman.repository.UserRepository;
import com.poly.fman.service.AddressService;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@Table(name = "address")
@NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private byte active;

	@Column(name = "is_default")
	private byte isDefault;

	@Lob
	private String address;

	@Column(name = "number_phone")
	private String numberPhone;

	@Column(name = "receiver_name")
	private String receiverName;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;


	// bi-directional many-to-one association to Order
	@OneToMany(mappedBy = "address")
	private List<Order> orders;

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setAddress(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setAddress(null);

		return order;
	}

	

}
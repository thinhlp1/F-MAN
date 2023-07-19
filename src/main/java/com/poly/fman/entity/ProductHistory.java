package com.poly.fman.entity;



import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the product_history database table.
 * 
 */
@Entity
@Table(name="product_history")
@NamedQuery(name="ProductHistory.findAll", query="SELECT p FROM ProductHistory p")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_at")
	private Date createAt;

	private BigInteger price;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

	@OneToOne
	@JoinColumn(name="create_by")
	private User createBy;

}
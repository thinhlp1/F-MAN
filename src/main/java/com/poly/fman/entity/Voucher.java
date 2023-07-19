package com.poly.fman.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import com.poly.fman.service.common.CommonUtils;
import com.poly.fman.service.common.DateUtils;

import java.math.BigInteger;

/**
 * The persistent class for the voucher database table.
 * 
 */
@Entity
@Table(name = "voucher")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Voucher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "active")
	private boolean active;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at")
	private Date createAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_at")
	private Date deleteAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_at")
	private Date endAt;

	@Column(name = "min_price")
	private BigInteger minPrice;

	@Column(name = "name")
	private String name;

	@Column(name = "sale_percent")
	private Float salePercent;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_at")
	private Date startAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at")
	private Date updateAt;

	// bi-directional one-to-one association to Order
	// @ManyToOne(mappedBy = "voucher")
	// private Order order;
	public String getMinPriceStringVnd() {
		return CommonUtils.convertToCurrencyString(minPrice, " VNĐ");
	}

	public String getSalePercentString() {
		return CommonUtils.convertToCurrencyString(salePercent, "%");
	}

	public String getCreateAtString() {
		return DateUtils.toString(createAt, "dd/MM/yyyy HH:mm:ss");
	}

	public String getStartAtString() {
		return DateUtils.toString(startAt, "dd/MM/yyyy HH:mm");
	}

	public String getEndAtString() {
		return DateUtils.toString(endAt, "dd/MM/yyyy HH:mm");
	}

	public String getToUpperCaseString() {
		return this.getName().toUpperCase();
	}
}
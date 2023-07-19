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

import com.poly.fman.service.common.CommonUtils;
import com.poly.fman.service.common.DateUtils;


/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@Table(name="orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_at")
	private Date createAt;

	private BigInteger total;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_at")
	private Date updateAt;

	@OneToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;

	//bi-directional many-to-one association to Address
	@ManyToOne
	private Address address;

	//bi-directional many-to-one association to PaymentMethod
	@ManyToOne
	@JoinColumn(name="payment_id")
	private PaymentMethod paymentMethod;

	@ManyToOne
	@JoinColumn(name="state_id")
	private OrderState orderState;

	//bi-directional one-to-one association to Voucher
	@OneToOne
	@JoinColumn(name="voucher_id")
	private Voucher voucher;

	@OneToOne
	@JoinColumn(name="update_by")
	private User updateBy;

	@Lob
	private String note;

	//bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy="order")
	private List<OrderItem> orderItems;

	public String getCreateAtString(){
		return DateUtils.toString(createAt, "dd/MM/yyyy HH:mm");
	}

	public String getTotalStringVND(){
		return CommonUtils.convertToCurrencyString(total, " VNĐ");
	}

	public String getTotalString(){
		return CommonUtils.convertToCurrencyString(total, "");
	}



}
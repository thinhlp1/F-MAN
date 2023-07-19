package com.poly.fman.entity;

import java.io.Serializable;
import java.util.Date;

import com.poly.fman.service.common.DateUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="transactions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name="order_id", referencedColumnName="id")
	private Order order;

	@Temporal(TemporalType.TIMESTAMP)
	private Date payDate;

	//bi-directional many-to-one association to Bank
	@ManyToOne
	@JoinColumn(name="bank_code")
	private Bank bank;
	
	private String bankNo;
	private String transNo;

	 public String getPayDateString(){
        return DateUtils.toString(payDate, "dd/MM/yyy HH:mm");
    }
}
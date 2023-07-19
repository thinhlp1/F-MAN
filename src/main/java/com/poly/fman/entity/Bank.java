package com.poly.fman.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="bank")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String bankCode;

	private boolean active;

	private String image;

	private String name;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="bank")
	private List<Transaction> transactions;
}
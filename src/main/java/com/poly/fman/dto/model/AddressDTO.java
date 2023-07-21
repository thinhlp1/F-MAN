package com.poly.fman.dto.model;

import com.poly.fman.entity.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO implements ModelDTO {
	private int id;

	private byte active;

	private byte isDefault;

	@NotBlank(message = "{NotBlank.address.address}")
	private String address;

	@NotBlank(message = "{NotBlank.address.numberPhone}")
	private String numberPhone;

	@NotBlank(message = "{NotBlank.address.receiverName}")
	private String receiverName;

	private Integer userId;

	private UserDTO user;
}

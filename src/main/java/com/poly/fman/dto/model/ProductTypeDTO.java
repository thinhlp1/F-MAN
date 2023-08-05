package com.poly.fman.dto.model;

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
public class ProductTypeDTO {

	@NotBlank(message = "{NotBlank.productType.id}")
	private String id;

	private byte active;

	@NotBlank(message = "{NotBlank.productType.name}")
	private String name;
}

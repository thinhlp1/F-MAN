package com.poly.fman.dto.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class SizeDTO implements ModelDTO, ResponseDTO {

	@NotNull(message = "{NotNull.size.id}")
	@NotBlank(message = "{NotBlank.size.id}")
	private String id;

	private byte active;

	@Min(value = 0, message = "{Min.size.length}")
	@Max(value = 100, message = "{Max.size.length}")
	@NotNull(message = "{NotNull.size.length}")
	private Float length;

	@Min(value = 35, message = "{Min.size.size}")
	@Max(value = 45, message = "{Max.size.size}")
	@NotNull(message = "{NotNull.size.size}")
	private Float size;

	@Min(value = 0, message = "{Min.size.width}")
	@Max(value = 100, message = "{Max.size.width}")
	@NotNull(message = "{NotNull.size.width}")
	private Float width;
}

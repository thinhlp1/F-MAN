package com.poly.fman.dto.model;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO implements ModelDTO, ResponseDTO {


	@NotBlank(message = "{NotBlank.brand.id}")
	private String id;
	
	private byte active;


	@NotBlank(message = "{NotBlank.brand.name}")
	private String name;
	
	private String image;
}

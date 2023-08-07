package com.poly.fman.dto.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodDTO implements ModelDTO {
	@NotBlank(message = "{NotBlank.payment.id}")
	private String id;

	private byte active;

	private String image;

	@NotBlank(message = "{NotBlank.payment.name}")
	private String name;

	@NotBlank(message = "{NotBlank.payment.accountNumber}")
	@Pattern(regexp = "^[0-9]+$", message = "{Pattern.payment.accountNumber}")
	private String account_number;
}

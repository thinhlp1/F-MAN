package com.poly.fman.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankDTO {
    private String bankCode;

	private boolean active;

	private String image;

	private String name;
}

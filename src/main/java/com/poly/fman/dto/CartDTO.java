package com.poly.fman.dto;

import java.util.Date;
import java.util.List;

import com.poly.fman.dto.reponse.SimpleReponseDTO;
import com.poly.fman.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO implements ModelDTO , ResponseDTO {
    
	private int id;

	
	private Date createAt;

	private int total;

	private String totalPrice;

	private Date updateAt;

	private UserDTO user;

	private List<CartItemDTO> cartItemsDTO;
}

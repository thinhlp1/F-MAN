package com.poly.fman.dto.cart;

import java.math.BigInteger;
import java.util.List;

import com.poly.fman.dto.model.ProductDTO;
import com.poly.fman.dto.model.ProductSizeDTO;
import com.poly.fman.dto.model.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDTO implements ResponseDTO {
   private Long total; 
   private List<CartItemResponseDTO> listCartItems;
}

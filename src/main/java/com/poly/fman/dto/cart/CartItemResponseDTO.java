package com.poly.fman.dto.cart;

import com.poly.fman.dto.model.ProductDTO;
import com.poly.fman.dto.model.ProductSizeDTO;
import com.poly.fman.dto.model.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDTO implements ResponseDTO {
    private ProductDTO productDTO;
    private ProductSizeDTO productSizeDTO;
    private int quantity;
    private Long subTotal;
}

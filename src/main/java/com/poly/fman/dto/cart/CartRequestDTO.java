package com.poly.fman.dto.cart;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRequestDTO {
    private int userId;
    private List<CartItemRequestDTO> listCartItem;
}

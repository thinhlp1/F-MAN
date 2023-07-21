package com.poly.fman.dto.cart;

import java.util.List;

import com.poly.fman.dto.model.AddressDTO;
import com.poly.fman.dto.model.PaymentMethodDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutReponseDTO {
    private List<CartItemResponseDTO> listCartItems;
    private List<AddressDTO> listAddressDTOs;
    private List<PaymentMethodDTO> listPaymentMethodDTOs;
    private Long total;
    private long discount;
    private long tempTotal;
}

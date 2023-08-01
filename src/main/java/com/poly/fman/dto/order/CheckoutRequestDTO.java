package com.poly.fman.dto.order;

import java.util.List;

import com.poly.fman.dto.cart.CartItemRequestDTO;
import com.poly.fman.dto.model.RequestDTO;
import com.poly.fman.dto.model.ResponseDTO;
import com.poly.fman.dto.payment.PaymentRquest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutRequestDTO implements RequestDTO {

    private List<CartItemRequestDTO> listCheckoutItem;
    private String paymentMethodId;
    private String bankCode;
    private Integer addressId;
    private String voucherCode;
    private int userId;

}

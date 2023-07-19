package com.poly.fman.dto.request;

import java.util.List;

import com.poly.fman.dto.payment.PaymentRquest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutDTO {
    private List<Integer> listItemId;
    private String paymentMethod;
    private Integer addressId;
    private String voucher;
    private int userId;
    private int quantity;
    private int productSizeId;
    private PaymentRquest paymentRequestDTO;
    private String isBuyNow;
    private String isReCheckout;
    private Integer orderId;
    // Constructors, getters, and setters

    // ...
}

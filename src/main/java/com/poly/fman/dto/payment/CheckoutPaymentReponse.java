package com.poly.fman.dto.payment;

import com.poly.fman.dto.reponse.SimpleReponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutPaymentReponse extends SimpleReponseDTO {
    private PaymentReponse paymentReponse;
    private Integer orderId;
}

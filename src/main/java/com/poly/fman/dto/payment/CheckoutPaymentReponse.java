package com.poly.fman.dto.payment;

import com.poly.fman.dto.model.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutPaymentReponse  implements ResponseDTO {
    private PaymentReponse paymentReponse;
    private Integer orderId;
}

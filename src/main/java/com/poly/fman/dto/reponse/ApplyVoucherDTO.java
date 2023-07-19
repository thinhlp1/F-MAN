package com.poly.fman.dto.reponse;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyVoucherDTO extends SimpleReponseDTO {

    public ApplyVoucherDTO(String statusCode, String message, String subTotal, String discount, String total){
        super(statusCode, message);
        this.subTotal =subTotal;
        this.discount = discount;
        this.total = total;
    }

    private String subTotal;
    private String discount;
    private String total;
}

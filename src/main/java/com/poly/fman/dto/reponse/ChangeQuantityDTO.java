package com.poly.fman.dto.reponse;

import java.math.BigInteger;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeQuantityDTO extends SimpleReponseDTO {
    private String total;
    private String subTotal;

    public ChangeQuantityDTO(String status, String mesage, String total, String subTotal) {
        super(status, mesage);
        this.total = total;
        this.subTotal = subTotal;
    }
}

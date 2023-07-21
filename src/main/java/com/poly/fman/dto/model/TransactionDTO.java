package com.poly.fman.dto.model;

import java.util.Date;

import com.poly.fman.service.common.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    String bankCode;
    Date payDate;
    int orderId;
    String bankNo;
    String transNo;

    public String getPayDateString(){
        return DateUtils.toString(payDate, "dd/MM/yyy HH:mm");
    }
}

package com.poly.fman.dto.reponse;

import java.math.BigInteger;

import com.poly.fman.dto.model.ResponseDTO;
import com.poly.fman.service.common.CommonUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyVoucherDTO implements ResponseDTO {
    
    private long tempTotal;
    private long discount;
    private long total;

    public String getTotalStringVND() {
      return CommonUtils.convertToCurrencyString(total, " VNĐ");
   }
   public String getTempTotalStringVND() {
      return CommonUtils.convertToCurrencyString(tempTotal, " VNĐ");
   }
   public String getDiscountStringVND() {
      return CommonUtils.convertToCurrencyString(discount, " VNĐ");
   }
}

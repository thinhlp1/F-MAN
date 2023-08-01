package com.poly.fman.dto.cart;

import java.util.List;

import com.poly.fman.dto.model.AddressDTO;
import com.poly.fman.dto.model.PaymentMethodDTO;
import com.poly.fman.dto.model.ResponseDTO;
import com.poly.fman.service.common.CommonUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutReponseDTO implements ResponseDTO{
    private List<CartItemResponseDTO> listCartItems;
    private List<AddressDTO> listAddress;
    private List<PaymentMethodDTO> listPaymentMethods;
    private Long total;
    private Long discount;
    private Long tempTotal;

    
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

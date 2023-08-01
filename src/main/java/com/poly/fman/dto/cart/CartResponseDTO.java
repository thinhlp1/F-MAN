package com.poly.fman.dto.cart;

import java.math.BigInteger;
import java.util.List;

import com.poly.fman.dto.model.ProductDTO;
import com.poly.fman.dto.model.ProductSizeDTO;
import com.poly.fman.dto.model.ResponseDTO;
import com.poly.fman.service.common.CommonUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDTO implements ResponseDTO {
   private Long total;
   private List<CartItemResponseDTO> listCartItems;

   public String getTotalStringVND() {
      return CommonUtils.convertToCurrencyString(total, " VNĐ");
   }

}

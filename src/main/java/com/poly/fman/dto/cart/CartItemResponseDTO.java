package com.poly.fman.dto.cart;

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
public class CartItemResponseDTO implements ResponseDTO {
    private ProductDTO product;
    private ProductSizeDTO productSize;
    private int quantity;
    private Long subTotal;

    public String getSubTotalStringVND(){
		return CommonUtils.convertToCurrencyString(subTotal, " VNĐ");
	}

}

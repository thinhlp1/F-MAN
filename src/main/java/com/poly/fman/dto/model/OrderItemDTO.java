package com.poly.fman.dto.model;

import java.math.BigInteger;

import com.poly.fman.entity.Order;
import com.poly.fman.entity.Product;
import com.poly.fman.entity.ProductSize;
import com.poly.fman.service.common.CommonUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO implements ModelDTO {

    private int id;

    private int quantity;

    private ProductDTO product;

    private ProductSizeDTO productSize;

    public String getTotalStringVND() {
        return CommonUtils.convertToCurrencyString(product.getPrice().intValue() * quantity, " VNĐ");
    }

     public Long getTotal() {
        return (long) (product.getPrice().intValue() * quantity);
    }
}

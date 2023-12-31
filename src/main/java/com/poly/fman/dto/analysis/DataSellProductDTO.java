package com.poly.fman.dto.analysis;

import com.poly.fman.dto.model.ProductDTO;
import com.poly.fman.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataSellProductDTO {
    private ProductDTO product;
    private long quantity ;
}

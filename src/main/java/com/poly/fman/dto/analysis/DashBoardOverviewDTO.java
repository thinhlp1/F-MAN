package com.poly.fman.dto.analysis;

import java.util.List;

import com.poly.fman.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashBoardOverviewDTO {
    private DataDTO listSoldProducts;
    private DataDTO listRevenue;
    private DataDTO listOrders;
    private List<Product> listTopProduct;
}

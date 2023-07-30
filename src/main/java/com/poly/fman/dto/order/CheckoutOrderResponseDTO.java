package com.poly.fman.dto.order;

import com.poly.fman.dto.model.OrderDTO;
import com.poly.fman.dto.model.ResponseDTO;
import com.poly.fman.service.common.CommonUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutOrderResponseDTO implements ResponseDTO {
   private OrderDTO order;
}

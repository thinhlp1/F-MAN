package com.poly.fman.dto.order;

import com.poly.fman.dto.model.OrderDTO;
import com.poly.fman.dto.model.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReCheckoutReponseDTO implements ResponseDTO{
    private OrderDTO order;

}

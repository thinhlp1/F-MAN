package com.poly.fman.dto.auth;

import com.poly.fman.dto.model.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminComponentDataDTO implements ResponseDTO {
    private int orderApprove;
    private String username;
    private String image;

}

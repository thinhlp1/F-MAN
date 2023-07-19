package com.poly.fman.dto.reponse;

import com.poly.fman.dto.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleReponseDTO implements ResponseDTO{
    protected String statusCode;
    protected String message;
}

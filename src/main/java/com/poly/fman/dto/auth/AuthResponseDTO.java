package com.poly.fman.dto.auth;

import com.poly.fman.dto.reponse.SimpleReponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO extends SimpleReponseDTO{
    private String accessToken;
    private String roleAccess;
    private String username;
    private int userId;
}

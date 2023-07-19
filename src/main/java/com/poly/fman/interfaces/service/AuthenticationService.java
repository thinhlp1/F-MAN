package com.poly.fman.interfaces.service;

import com.poly.fman.dto.auth.AuthResponseDTO;
import com.poly.fman.dto.auth.LoginDTO;
import com.poly.fman.dto.auth.RegisterDTO;

public interface AuthenticationService {
    public AuthResponseDTO authenticate(LoginDTO loginDTO);

    public AuthResponseDTO register(RegisterDTO registerDTO);
}
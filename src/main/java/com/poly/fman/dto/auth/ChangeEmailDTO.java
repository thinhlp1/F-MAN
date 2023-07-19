package com.poly.fman.dto.auth;

import com.poly.fman.config.annotation.FieldMatch;
import com.poly.fman.dto.ResponseDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeEmailDTO implements ResponseDTO{
    private String username;


    @NotBlank(message = "{NotBlank.user.email}")
    @Email(message = "{Email.user.email}")
    private String newEmail;

    private String otp;
    
}

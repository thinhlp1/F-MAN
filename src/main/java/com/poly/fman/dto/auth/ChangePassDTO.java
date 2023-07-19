package com.poly.fman.dto.auth;

import com.poly.fman.config.annotation.FieldMatch;

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
public class ChangePassDTO {


    private String username;

    @NotBlank
    @Size(min=8, max=32)
    private String oldPassword;

    @NotBlank
    @Size(min=8, max=32)
    private String newPassword;

}

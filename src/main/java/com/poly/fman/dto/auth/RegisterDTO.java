package com.poly.fman.dto.auth;




import com.poly.fman.config.annotation.FieldMatch;
import com.poly.fman.dto.model.ResponseDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO implements ResponseDTO {

    @NotBlank
    @Email
    private String username;

    @NotBlank
    @Size(min=8, max=32)
    private String password;

    @NotBlank
    private String name;


    @NotBlank
    @Pattern(regexp = "(09|03|07|08)\\d{8}", message = "{Pattern.account.numberPhone}")
    private String numberPhone;

}

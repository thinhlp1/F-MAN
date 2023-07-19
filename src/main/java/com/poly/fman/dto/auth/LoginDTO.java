package com.poly.fman.dto.auth;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO  {
    
    @NotNull
    @NotBlank
    @Email
    private String username;

    @NotBlank
    @Size(min=8, max=32)
    private String password;

    private boolean remember;

    public boolean getRemberme(){
        return this.remember;
    }

    public void setRemberme(boolean remember){
         this.remember = remember;
    }
}

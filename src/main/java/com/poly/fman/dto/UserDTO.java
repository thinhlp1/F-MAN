package com.poly.fman.dto;

import java.util.Date;

import javax.swing.SpringLayout.Constraints;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poly.fman.entity.Role;
import com.poly.fman.interfaces.ValidPassword;
import com.poly.fman.service.common.DateUtils;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements ModelDTO, ResponseDTO {

	private int id;

	private boolean active;

	private Date createAt;

	private Date deleteAt;

	private Date updateAt;

	@NotBlank(message = "{NotBlank.user.email}")
	@Email(message = "{Email.user.email}")
	private String email;

	private String image;

	@NotBlank(message = "{NotBlank.user.name}")
	@Size(min = 5, max = 50, message = "{Size.user.name}")
	private String name;

	@Pattern(regexp = "^[\\d]+$", message = "{Pattern.user.numberPhone}")
	@NotBlank(message = "{NotBlank.user.numberPhone}")
	@Size(min = 10, max = 10, message = "{Size.user.numberPhone}")
	private String numberPhone;


	@NotNull(message = "{NotNull.user.password}")
	@ValidPassword
    @JsonIgnore
	private String password;

	private String username;

    @JsonIgnore
	private RoleDTO role;

}

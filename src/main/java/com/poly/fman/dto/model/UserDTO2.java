package com.poly.fman.dto.model;

import java.util.Date;

import javax.swing.SpringLayout.Constraints;

import com.poly.fman.entity.Role;
import com.poly.fman.interfaces.ValidPassword;

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
public class UserDTO2 implements ModelDTO, ResponseDTO {

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

	/*
	 * ^ # start-of-string
	 * (?=.*[0-9]) # a digit must occur at least once
	 * (?=.*[a-z]) # a lower case letter must occur at least once
	 * (?=.*[A-Z]) # an upper case letter must occur at least once
	 * (?=.*[@#$%^&+=]) # a special character must occur at least once
	 * (?=\S+$) # no whitespace allowed in the entire string
	 * .{8,} # anything, at least eight places though
	 * $ # end-of-string
	 */

	@NotNull(message = "{NotNull.user.password}")
	@ValidPassword
	private String password;

	// @NotNull(message = "{NotNull.user.username}")
	// @Size(min = 5, max = 50, message = "{Size.user.username}")
	private String username;

	private String roleId;

	private Role role;

}

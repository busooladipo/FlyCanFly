package com.fly.flycanfly.dto;

import com.fly.flycanfly.validation.Password;
import com.fly.flycanfly.validation.PasswordMatches;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
@PasswordMatches
public class UserAccountDto {
    @NotBlank(message = "Username is mandatory")
    private String username;
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Password
    private String password;
    @NotBlank(message = "Password confirmation is mandatory")
    private String confirmPassword;

}

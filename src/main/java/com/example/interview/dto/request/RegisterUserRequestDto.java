package com.example.interview.dto.request;

import com.example.interview.entity.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterUserRequestDto {
    @NotBlank(message = "The email is required.")
    @Email(message = "The email address is invalid.", flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String email;

    @NotBlank(message = "The password is required.")
    private String password;

    @NotBlank(message = "The name is required.")
    private String name;

    @NotNull(message = "The role is required.")
    private Role role;

    private String phoneNumber;
}

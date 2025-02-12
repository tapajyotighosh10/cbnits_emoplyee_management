package com.cbnits.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    @NotBlank(message = "Username cannot be empty")
    private String userName;

    @NotBlank(message = "Role cannot be empty")
    private String role;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=?<>]).{8,}$",
            message = "Password must contain at least one uppercase letter, one number, and one special character"
    )
    private String password;
}


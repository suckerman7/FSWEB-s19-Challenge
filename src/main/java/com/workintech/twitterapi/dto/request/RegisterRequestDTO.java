package com.workintech.twitterapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    private String username;

    @Email
    private String email;

    @NotBlank
    private String password;
}

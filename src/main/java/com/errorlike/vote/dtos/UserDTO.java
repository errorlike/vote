package com.errorlike.vote.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String matchingPassword;
    @NotBlank
    private String email;
}

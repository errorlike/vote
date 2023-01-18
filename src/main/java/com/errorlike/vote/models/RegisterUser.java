package com.errorlike.vote.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterUser {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String matchingPassword;

    @NotBlank
    private String email;

    @NotNull
    @Size(min = 0, max = 120)
    private Integer age;
}

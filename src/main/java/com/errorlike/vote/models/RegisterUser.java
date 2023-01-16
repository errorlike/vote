package com.errorlike.vote.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @Min(0)
    @Max(120)
    private Integer age;
}

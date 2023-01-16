package com.errorlike.vote.models;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class loginRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
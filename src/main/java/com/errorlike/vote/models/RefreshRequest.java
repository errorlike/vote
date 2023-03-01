package com.errorlike.vote.models;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RefreshRequest {
    @NotBlank
    private String refreshToken;
}

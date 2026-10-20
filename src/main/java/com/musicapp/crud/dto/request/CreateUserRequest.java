package com.musicapp.crud.dto.request;
import jakarta.validation.constraints.*;
public record CreateUserRequest(
    @NotBlank @Size(max = 50) String username,
    @NotBlank @Email String email,
    @NotBlank String passwordHash,
    String displayName, String profileImage, String bio, String country, String subscriptionType
) {}

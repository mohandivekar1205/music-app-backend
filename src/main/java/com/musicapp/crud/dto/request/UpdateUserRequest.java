package com.musicapp.crud.dto.request;
import jakarta.validation.constraints.*;
public record UpdateUserRequest(
    @Size(max = 50) String username,
    @Email String email,
    String displayName, String profileImage, String bio, String country, String subscriptionType
) {}

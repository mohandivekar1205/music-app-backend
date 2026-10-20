package com.musicapp.crud.dto.response;
import java.time.Instant;
public record UserResponse(
    Long userId, String username, String email, String displayName,
    String profileImage, String bio, String country, String subscriptionType,
    Instant createdAt, Instant updatedAt
) {}

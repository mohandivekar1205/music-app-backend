package com.musicapp.crud.dto.request;
import jakarta.validation.constraints.*;
public record CreatePodcastRequest(
    @NotBlank String title, @NotBlank String author, String coverImage,
    String description, String category, String language
) {}

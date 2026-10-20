package com.musicapp.crud.dto.request;
import jakarta.validation.constraints.*;
public record CreatePlaylistRequest(
    @NotBlank String name, String description, String coverImage, @NotBlank String visibility
) {}

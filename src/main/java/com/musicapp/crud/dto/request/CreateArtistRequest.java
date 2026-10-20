package com.musicapp.crud.dto.request;
import jakarta.validation.constraints.*;
public record CreateArtistRequest(
    @NotBlank String name, String coverImage, String biography, Boolean verified
) {}

package com.musicapp.crud.dto.request;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
public record CreateAlbumRequest(
    @NotBlank String title, String coverImage, LocalDate releaseDate, Integer releaseYear,
    @NotBlank String albumType, String description, @NotNull Long artistId
) {}

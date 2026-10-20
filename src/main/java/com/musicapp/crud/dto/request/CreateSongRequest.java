package com.musicapp.crud.dto.request;
import jakarta.validation.constraints.*;
public record CreateSongRequest(
    @NotBlank String title, Integer duration, Integer trackNumber, String audioUrl,
    String coverImage, String lyricsUrl, String status, @NotNull Long albumId
) {}
